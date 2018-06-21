package com.senpure.demo;

import com.senpure.base.AppEvn;
import com.senpure.base.generator.Config;
import com.senpure.base.generator.DatabaseGenerator;

/**
 * Created by 罗中正 on 2018/1/9 0009.
 */
public class DemoGenerator {
    public static void main(String[] args) {

      //

        System.setProperty("PID", AppEvn.getPid());
        DatabaseGenerator mybatisGenerator = new DatabaseGenerator();
        System.out.println(AppEvn.getClassRootPath());
        Config config = new Config();
        config.setAllCover(true);
        config.setSingleCheck(true);

        config.setUserCriteriaStr(true);
        config.setMenuStartId(1000);

        config.setDefaultCache(true,false,false,true);

        mybatisGenerator.generate("com.senpure.demo",config);
    }
}
