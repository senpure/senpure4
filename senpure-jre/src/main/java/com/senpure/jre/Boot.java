package com.senpure.jre;


import com.senpure.base.AppEvn;
import com.senpure.base.util.BannerShow;
import org.fusesource.jansi.AnsiConsole;
import org.springframework.boot.ansi.AnsiOutput;

/**
 * Created by 罗中正 on 2017/7/20.
 */
public class Boot {

    public static void main(String[] args) {
       // RuntimeMXBean runtimeMXBean=   ManagementFactory.getRuntimeMXBean();
        AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
        AnsiConsole.systemInstall();
        AnsiConsole.systemUninstall();
        System.setProperty("PID", AppEvn.getPid());
        BannerShow.show();
        Executer executer=new Executer();
        Config config=new Config();
        executer.analyzeTarget(config);
    }
}
