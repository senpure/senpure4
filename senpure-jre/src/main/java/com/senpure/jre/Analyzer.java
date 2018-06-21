package com.senpure.jre;


import com.senpure.base.AppEvn;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by 罗中正 on 2017/7/20.
 */
public class Analyzer {
    static {
        System.setProperty("PID", AppEvn.getPid());
    }

    // private static  Logger logger = LoggerFactory.getLogger(Analyzer.class);
    private static int targetCount = 0;
    private static int libCount = 0;


    private static List<File> getFiles(File dir, String suffix) {
        File[] source = dir.listFiles();
        List<File> files = new ArrayList();
        for (File file : source) {
            if (file.isDirectory()) {
                files.addAll(getFiles(file, suffix));
            } else {
                if (file.getName().endsWith(suffix)) {
                    files.add(file);
                }

            }

        }
        return files;
    }

    private static List<File> getClass(File dir) {
        return getFiles(dir, ".class");
    }

    private static List<File> getJar(File dir) {
        return getFiles(dir, ".jar");
    }

    private static String getFilePathName(String dir, File file) {
        String p = file.getAbsolutePath().replace(dir + File.separator, "");
        return p;
    }

    public void analyzeLib(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            List<File> jars = getJar(file);
            System.out.println("jars==========" + jars);
            for (File jar : jars) {
                libCount += analyze(jar.getAbsolutePath());
            }
        } else {
            libCount += analyze(path);
        }

    }

    public void analyzeTarget(String path) {

        targetCount = analyze(path);
    }

    public int analyze(String path) {
        File file = new File(path);
        ClassLoader classLoader = null;
        try {
            classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        int count = 0;
        if (file.isDirectory()) {
            List<File> clazzs = getClass(file);
            for (File f : clazzs) {
                // System.out.println(f.getAbsolutePath());
                String className = getFilePathName(path, f).replace("/", ".")
                        .replace(".class", "")
                        .replace(File.separator, ".");

                try {
                    // System.out.println(className);

                    // System.out.println("className " + className);
                    Class c = classLoader.loadClass(className);

                    count++;
                    //  Class.forName(className);
                } catch (Exception e) {
                    System.out.println("error " + className + "," + e.toString());
                }
                catch (NoClassDefFoundError e)
                {
                    System.out.println("error " + className + "," + e.toString());
                }


            }
        } else {

            try {
                JarFile jarFile = new JarFile(path);
                Enumeration<JarEntry> entrys = jarFile.entries();
                System.out.println("分析jar " + jarFile.getName());

                while (entrys.hasMoreElements()) {
                    JarEntry jarEntry = entrys.nextElement();
                    String name = jarEntry.getName();

                    if (name.startsWith("BOOT-INF")) {
                    name=    name.replace("BOOT-INF/classes/","");

                    }
                    //  System.out.println(name);
                    if (name.endsWith(".class")||name.endsWith(".jar")) {
                        count++;
                        name = name.replace(".class", "")
                                .replace("/", ".")

                                .replace(File.separator, ".");
                        //  System.out.println(name);
                        //Class.forName(name);
                        try {
                            Class c = classLoader.loadClass(name);

                        } catch (ClassNotFoundException e) {
                            // e.printStackTrace();
                        } catch (NoClassDefFoundError e) {
                            System.out.println("AnalyerError:" + e.toString());
                            //e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(e.toString());

            }

        }
        return count;
    }


    public static void main(String[] args) {
        // AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
        // AnsiConsole.systemInstall();
        // AnsiConsole.systemUninstall();
        Analyzer analyzer = new Analyzer();
        // String path="E:\\projects\\senpure\\target\\classes";
        //  System.out.println("entry analyer"+args[0]);

        String target = System.getProperty("jre.target");
        String lib = System.getProperty("jre.lib");


        if (target == null) {
            target = "C:\\Users\\admin\\Desktop\\Greenjvm_make\\fps_test.jar";
        }
        analyzer.analyzeTarget(target);
        if (lib != null) {
            analyzer.analyzeLib(lib);
        }


        System.out.println("共读取目标class " + targetCount);
        System.out.println("共读取第三方class " + libCount);
        System.out.println("共读取class " + (libCount + targetCount));


    }
}
