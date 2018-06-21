package com.senpure.base;

import com.senpure.base.util.Assert;
import com.senpure.base.util.StringUtil;
import org.fusesource.jansi.AnsiConsole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ansi.AnsiOutput;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 应用环境
 */
public class AppEvn {
    static {
        System.setProperty("PID", AppEvn.getPid());
    }

    private static String classRootPath;
    private static Logger logger = LoggerFactory.getLogger(AppEvn.class);

    public static boolean isWindowsOS() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("windows");
    }

    public static boolean isLinuxOS() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("linux");
    }

    /**
     * 获取class\jar的根路径如<br>
     * E:\projects\com.senpure.base\target\classes\com\senpure\AppEvn.class ->
     * E:\projects\com.senpure.base\target\classes<br>
     * E:\projects\com.senpure.base\target\<b><i>jar.jar</i></b>\com\senpure\AppEvn.class ->
     * E:\projects\com.senpure.base\target
     *
     * @return
     */
    public static String getClassRootPath(Class clazz) {
        String classRootPath = null;
        try {
            URL url = clazz.getResource("");
            if (url == null) {
                return getClassRootPath();
            }
            URI uri = url.toURI();

            classRootPath = uri.getPath();
            boolean cutPackage = true;
            if (classRootPath == null) {
                cutPackage = false;
                classRootPath = getJarRootPath(uri);
                uri = new URI(classRootPath);
                classRootPath = uri.getPath();
            }
            if (isWindowsOS()) {
                int index = classRootPath.indexOf("/");
                if (index == 0) {
                    classRootPath = classRootPath.substring(1);
                }
            }
            classRootPath = classRootPath.replace("/", File.separator);
            if (cutPackage) {
                if (clazz.getPackage() != null) {
                    String packpath = clazz.getPackage().getName();
                    packpath = packpath.replace(".", File.separator);
                    classRootPath = classRootPath.replace(packpath, "");
                }
            }
            while (classRootPath.charAt(classRootPath.length() - 1) == File.separatorChar) {
                classRootPath = classRootPath.substring(0, classRootPath.length() - 1);
            }

        } catch (URISyntaxException e) {

            e.printStackTrace();
        }
        return classRootPath;
    }

    /**
     * 存在多个classpath 时该值不准确,推荐使用带参数的getClassRootPath(Class clazz)<br>
     * <p>
     * 需要先调用 AppEvn.markClassRootPath()|| AppEvn.markClassRootPath(Class clazz)
     *
     * @return
     */
    public static String getClassRootPath() {

        Assert.notNull(classRootPath, "请先标记classRootPath 调用 AppEvn.markClassRootPath()|| AppEvn.markClassRootPath(Class clazz)");
//        if (classRootPath == null) {
//            markClassRootPath();
//        }
        return classRootPath;
    }

    public static void markClassRootPath() {
        StackTraceElement[] statcks = Thread.currentThread()
                .getStackTrace();
        StackTraceElement statck = statcks[2];
        try {
            Class clazz = Class.forName(statck.getClassName());
            markClassRootPath(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void markClassRootPath(Class clazz) {
        String oldClassRootPath = classRootPath;
        classRootPath = getClassRootPath(clazz);
        if (oldClassRootPath != null && !classRootPath.equals(oldClassRootPath)) {
            String nowClassRootPath = classRootPath;
            classRootPath = oldClassRootPath;
            Assert.error("两个不相同的标识" + classRootPath + "," + nowClassRootPath);
        }
    }


    public static String getCallerRootPath() {
        return getClassRootPath(getCallerClass());
    }

    public static Class getCallerClass() {
        StackTraceElement[] statcks = Thread.currentThread()
                .getStackTrace();
        StackTraceElement statck = statcks[3];
        Class clazz = null;
        try {
            clazz = Class.forName(statck.getClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    /**
     * @param clazz 不能是AppEvn.class
     * @return
     */
    public static boolean classInJar(Class clazz) {
        URL url = clazz.getResource("");
        logger.trace("clazz {}  url {}", url, clazz.getName());
        if (url == null) {
            return true;
        }
        return url.toString().contains("jar:file:");
    }


    public static boolean callerInJar() {
        return classInJar(getCallerClass());
    }

    private static String getJarRootPath(URI uri) {
        String location = uri.toString();
        int index = location.indexOf(".jar!");
        if (index == -1) {
            index = StringUtil.indexOf(location, ".", 1, true);
        }
        location = location.substring(0, index);
        index = StringUtil.indexOf(location, "/", 1, true);
        location = location.substring(0, index);
        location = location.replace("jar:file:", "");
        return location;
    }

    private static String getJarPath(String location) {
        int index = location.indexOf(".jar!") + 4;
        if (index == 3) {
            index = StringUtil.indexOf(location, "!", 1, true);
        }
        location = location.substring(0, index);
        location = location.replace("jar:file:", "");
        return location;
    }

    public static String getClassPath(Class clazz) {
        URL url = clazz.getResource("");
        try {
            URI uri = url.toURI();
            String location = uri.getPath();
            if (location == null) {
                location = getJarPath(uri.toString());
                location = new URI(location).getPath();
            } else {
                location += clazz.getSimpleName() + ".class";
            }
            if (isWindowsOS()) {
                int index = location.indexOf("/");
                if (index == 0) {
                    location = location.substring(1);
                }
            }
            location = location.replace("/", File.separator);

            while (location.charAt(location.length() - 1) == File.separatorChar) {
                location = location.substring(0, location.length() - 1);
            }
            return location;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCallerPath() {

        return getClassPath(getCallerClass());
    }

    public static String getPid() {
        try {
            String jvmName = ManagementFactory.getRuntimeMXBean().getName();
            return jvmName.split("@")[0];
        } catch (Throwable ex) {
            return null;
        }
    }

    public static void installAnsiConsole(Class clazz) {
        try {
            Class.forName("org.fusesource.jansi.AnsiConsole");
            AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
            if (AppEvn.classInJar(clazz)) {
                String name=clazz.getResource("").toString();
                if (!name.contains("debugger-agent-storage.jar")&&!name.contains("deploy.jar")) {
                    AnsiConsole.systemInstall();
                }
            }
        } catch (ClassNotFoundException e) {
            logger.info("不适用控制台彩色日志");
        }
    }

    public static void installAnsiConsole() {

        StackTraceElement[] statcks = Thread.currentThread()
                .getStackTrace();
        StackTraceElement statck = statcks[2];
        Class clazz = null;
        try {
            clazz = Class.forName(statck.getClassName());

            installAnsiConsole(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void WaitForGodot() {

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
