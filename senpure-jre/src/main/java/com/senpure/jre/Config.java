package com.senpure.jre;

/**
 * Created by 罗中正 on 2017/7/25.
 */
public class Config {

    //目标class根目录，可以是jar完整路径
   //private String targetPath="E:\\com\\senpure\\classes";
  //  private String targetPath="E:\\projects\\senpure\\target\\senpure.jar";
   //private String targetPath="C:\\Users\\admin\\Desktop\\Greenjvm_make\\fps_test.jar";
    private String targetPath="E:\\projects\\senpure-tools\\senpure-generator\\target\\senpure-generator-1.0-SNAPSHOT.jar";
   private String targetLib="";

   //是否用了反射
   private boolean reflect=true;
  //  private String targetLib="E:\\com\\senpure\\lib";
    //如果是jar，该值会代替jar的main 方法
  //  private String targetRunClass="com.senpure.Boot";
    private String targetRunClass="";

    //是否是web程序
    private boolean  targetWeb=false;
    private int  targetWebPort=80;
    private String targetWebContext="/";
    private String targetJreDirectory="E:\\jardir";

    public String getTargetRunClass() {
        return targetRunClass;
    }

    public void setTargetRunClass(String targetRunClass) {
        this.targetRunClass = targetRunClass;
    }

    public int getTargetWebPort() {
        return targetWebPort;
    }

    public void setTargetWebPort(int targetWebPort) {
        this.targetWebPort = targetWebPort;
    }

    public String getTargetWebContext() {
        return targetWebContext;
    }

    public void setTargetWebContext(String targetWebContext) {
        this.targetWebContext = targetWebContext;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public String getTargetLib() {
        return targetLib;
    }

    public void setTargetLib(String targetLib) {
        this.targetLib = targetLib;
    }

    public String getTargetJreDirectory() {
        return targetJreDirectory;
    }

    public void setTargetJreDirectory(String targetJreDirectory) {
        this.targetJreDirectory = targetJreDirectory;
    }

    public boolean isTargetWeb() {
        return targetWeb;
    }

    public void setTargetWeb(boolean targetWeb) {
        this.targetWeb = targetWeb;
    }

    public boolean isReflect() {
        return reflect;
    }

    public void setReflect(boolean reflect) {
        this.reflect = reflect;
    }
}
