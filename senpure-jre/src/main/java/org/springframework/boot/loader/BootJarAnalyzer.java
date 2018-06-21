package org.springframework.boot.loader;

/**
 * Created by 罗中正 on 2017/7/27.
 */
public class BootJarAnalyzer {

    public ClassLoader loadClass() throws Exception {
        JarLauncher launcher = new ReadJarLanuncher();
        //launcher.launch(args);
        ClassLoader classLoader = launcher.createClassLoader(launcher.getClassPathArchives());
        return classLoader;
    }

    public static void main(String[] args) throws Exception {

        BootJarAnalyzer analyzer = new BootJarAnalyzer();


    }
}
