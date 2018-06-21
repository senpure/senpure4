package com.senpure.jre;

import com.senpure.base.util.StringUtil;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.archivers.jar.JarArchiveInputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by 罗中正 on 2017/7/20.
 */
public class Executer {


    private static Logger logger = LoggerFactory.getLogger(Executer.class);
    private static Logger runLogger = LoggerFactory.getLogger("com.senpure.jre.Run");
    private static Logger readLogger = LoggerFactory.getLogger("com.senpure.jre.Read");

    private boolean readOver = false;
    private long readTime = 0;
    private Process p;

    public void http(Config config) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("http://127.0.0.1");
            if (config.getTargetWebPort() != 80) {
                sb.append(":").append(config.getTargetWebPort());
            }
            if (config.getTargetWebContext() != null) {
                sb.append(config.getTargetWebContext());
            }

            URL realUrl = new URL(sb.toString());
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "java1.8");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            connection.setRequestProperty("Cookie", "1");

            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            while (in.readLine() != null) {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void analyzeClass(List<String> clazzs, Config config) {
        List<String> opened = new ArrayList<>();
        Map<String, List<String>> loadedMap = new HashMap<>(16);
        for (int i = 0; i < clazzs.size(); i++) {
            String str = clazzs.get(i);
            if (str.startsWith("[Opened")) {
                logger.debug(str);
                str = str.replace("[Opened ", "");
                opened.add(str);
                List<String> list = new ArrayList<>();
                loadedMap.put(str, list);
                clazzs.remove(i);
                i--;

            }
        }
        int count = 0;

        for (int i = 0; i < clazzs.size(); i++) {

            String str = clazzs.get(i);
            for (String o : opened) {
                if (str.endsWith(o)) {
                    count++;
                    str = str.replace("[Loaded ", "")
                            .replace(" from " + o, "")
                            .trim()
                            .replace(".", "/") + ".class"
                    //.replace("]","")
                    ;
                    loadedMap.get(o).add(str);
                    break;
                }
            }
        }
        Iterator<Map.Entry<String, List<String>>> iterator = loadedMap.entrySet().iterator();

        File dir = new File(config.getTargetJreDirectory());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
        List<String> logs = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<String, List<String>> entry = iterator.next();

            String jar = entry.getKey();
            jar = jar.replace("]", "");
            File jarFile = new File(jar);
            String name = jar;
            int index = StringUtil.indexOf(jar, "\\", 1, true);
            if (index == -1) {
                logger.debug("没有找到 文件分割符");
                index = StringUtil.indexOf(jar, File.separator, 1, true);
            }
            if (index > -1) {
                name = name.substring(index + 1);
            }
            File target = new File(dir, name);


            try {

                ArchiveOutputStream outputStream = new JarArchiveOutputStream(new FileOutputStream(target));
                JarArchiveInputStream inputStream = new JarArchiveInputStream(new FileInputStream(jarFile));
                ArchiveEntry archiveEntry = inputStream.getNextEntry();
                List<String> clazz = entry.getValue();
                int jarCount = 0;
                int c = 0;
                while (archiveEntry != null) {
                    boolean move = false;
                    String aname = archiveEntry.getName();
                    if (archiveEntry.getName().endsWith(".class")) {

                        // logger.debug(archiveEntry.getName());
                        jarCount++;
                        for (String clz : clazz) {
                            if (clz.equals(archiveEntry.getName())
                                // ||archiveEntry.getName().endsWith("Exception.class")
                                //||archiveEntry.getName().startsWith("java/lang/reflect")
                                //||archiveEntry.getName().startsWith("java/security")
                                //||archiveEntry.getName().startsWith("com/sun/javafx")
                                    ||archiveEntry.getName().startsWith("java/io")
                                    ) {
                                c++;
                                move = true;
                                break;
                            }
                            if (config.isReflect() && archiveEntry.getName().startsWith("sun/reflect")) {
                                c++;
                                move = true;
                                break;
                            }

                        }

                        int inner = aname.indexOf("$");
                        if (inner > -1) {
                            aname = aname.substring(0, inner) + ".class";
                            if (clazz.contains(aname)) {
                                c++;
                                move = true;
                            }
                        }


                    } else {
                        move = true;
                    }
                    if (move) {
                        JarArchiveEntry jare = new JarArchiveEntry(archiveEntry.getName());
                        //  logger.debug("抽取 {} > {}:{}", jar, target, archiveEntry.getName());
                        outputStream.putArchiveEntry(jare);
                        IOUtils.copy(inputStream, outputStream);

                        outputStream.closeArchiveEntry();
                    }
                    archiveEntry = inputStream.getNextEntry();
                }

                inputStream.close();
                outputStream.finish();
                long jl = jarFile.length();

                StringBuilder log = new StringBuilder();
                log.append(jar)
                        .append(" > ").append(target.getAbsolutePath())

                        .append(",jar拥有类 ")
                        .append(jarCount).append(",抽取类 ")
                        .append(c)
                        .append(",jar 大小 ")
                        .append(jl >> 10).append("k")
                        .append(",抽取大小 ")
                        .append(target.length() >> 10).append("k")
                        .append(",瘦身 ")
                        .append(nf.format((jl - target.length() + 0D) / jl));
                logs.add(log.toString());
                //logger.debug("{}拥有类 {} 抽取类 {}", jar, jarCount, c);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        for (String log : logs) {
            logger.debug(log);
        }
    }

    public void analyzeTarget(Config config) {
        String targetPath = config.getTargetPath();
        String libPath = config.getTargetLib();

        File lib = null;
        if (libPath != null && libPath.trim().length() > 0) {
            lib = new File(libPath);
        }
        String command = targetCommand(targetPath, libPath, lib);


        Set<String> clazz = new HashSet<String>();
        List<String> list = new ArrayList<>(1024);
        //clazz.addAll(loadClass(command, list, false));
        StringBuilder runCommand = null;

        if (config.getTargetRunClass() != null && config.getTargetRunClass().length() > 2) {
            runCommand = prepCommand(targetPath, libPath, lib);
            runCommand.append(" ");
            runCommand.append(config.getTargetRunClass());
            clazz.addAll(readRunTarget(runCommand.toString(), config));

        } else {
            //run jar
            runCommand = prepCommand(targetPath, libPath, lib);
            runCommand.append(" -Dext.method=true");
            runCommand.append(" -jar").append(" ").append(targetPath);
            clazz.addAll(readRunTarget(runCommand.toString(), config));
        }

        List<String> clazzs = new ArrayList<>(clazz);
        analyzeClass(clazzs, config);

    }

    public List<String> readRunTarget(String commandStr, Config config) {
        List<String> list = new ArrayList<>(1024);
        Thread thread = new Thread(() -> loadClass(commandStr, list, true));
        thread.setName("RUN-TARGET");
        thread.setDaemon(true);

        logger.debug(" 运行目标程序，请尽可能操作程序");
        thread.start();
        readTime = System.currentTimeMillis();
        readOver = false;
        boolean down = false;
        boolean http = false;
        while (!readOver) {
            long time = System.currentTimeMillis() - readTime;
            if (time > 10000) {
                logger.debug("停止目标程序运行 {}", thread.getId());
                thread.interrupt();
                p.destroy();
                break;

            } else if (time > 3000) {
                if (!http) {
                    if (config.isTargetWeb()) {
                        http(config);
                    }
                    http = true;
                    readTime = System.currentTimeMillis();
                }


                down = true;
                logger.warn(" {} 毫秒后停止运行目标程序,请尽可能操作程序", 10000 - time);


            } else {
                if (down) {
                    down = false;
                    logger.debug(" 正在运行目标程序，请尽可能操作程序");
                }
            }
            try {
                //  logger.debug("正在读取目标所需class");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public String targetCommand(String targetPath, String libPath, File lib) {
        StringBuilder command = new StringBuilder();
        command.append("java -verbose:class");
        command.append(" -Dfile.encoding=UTF-8 ");
        command.append(" -classpath ");
        command.append("\"");
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

        command.append(runtimeMXBean.getClassPath());
        command.append(";").append(targetPath);
        if (lib != null) {
            if (lib.isDirectory()) {
                command.append(";").append(lib + "\\*");
            } else {
                command.append(";").append(lib);
            }
        }
        command.append("\"");

        command.append(" -Djre.target=").append(targetPath);
        if (lib != null) {
            command.append(" -Djre.lib=").append(libPath);
        }
        command.append(" com.senpure.jre.Analyzer");
        return command.toString();
    }

    public StringBuilder prepCommand(String targetPath, String libPath, File lib) {

        StringBuilder command = new StringBuilder();
        command.append("java -verbose:class");
        command.append(" -classpath ");
        command.append("\"");
        //   command.append(runtimeMXBean.getClassPath());
        command.append("").append(targetPath);
        if (lib != null) {
            if (lib.isDirectory()) {
                command.append(";").append(lib + "\\*");
            } else {
                command.append(";").append(lib);
            }
        }
        command.append("\"");
        command.append(" -Dfile.encoding=UTF-8 ");
        return command;
    }


    public List<String> loadClass(String commandStr, List<String> clazzs, boolean runTarget) {
        logger.debug("command {}", commandStr);
        BufferedReader br = null;

        try {
            p = Runtime.getRuntime().exec(commandStr);

            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            // StringBuilder sb = new StringBuilder();
            int count = 0;

            readOver = false;
            while ((line = br.readLine()) != null || br.ready()) {

                line = URLDecoder.decode(line, "utf-8");
                // logger.debug(line);
                // sb.append(line + "\n");

                if (line.startsWith("[Loaded")) {
                    // logger.debug(line);
                    count++;
                    readTime = System.currentTimeMillis();
                    clazzs.add(line);
                } else if (line.startsWith("[Opened")) {
                    clazzs.add(line);
                } else {
                    if (runTarget) {
                        runLogger.debug(line);
                    } else {

                        readLogger.debug(line);
                    }
                }

            }
            //  logger.debug(sb.toString());
            logger.debug("loaded class =" + count);
            readOver = true;
            // analyzeClass(clazzs);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return clazzs;
    }

    public static void main(String[] args) {


        Executer executer = new Executer();

        Analyzer analyzer = new Analyzer();

        // analyzer.analyze("E:\\projects\\senpure\\target\\classes");
        long jl = 645090;
        long l = 130364;
        System.out.println(jl >> 11);
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
        System.out.println(nf.format(l * 1d / jl));
    }
}
