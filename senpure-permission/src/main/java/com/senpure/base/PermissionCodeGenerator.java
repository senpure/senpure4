package com.senpure.base;

import com.senpure.base.generator.Config;
import com.senpure.base.generator.DatabaseGenerator;

/**
 * Created by 罗中正 on 2017/10/11.
 */
public class PermissionCodeGenerator {
    public static void main(String[] args) {


        AppEvn.getClassRootPath();
        System.setProperty("PID", AppEvn.getPid());
        DatabaseGenerator mybatisGenerator = new DatabaseGenerator();
        Config config = new Config();
        config.setAllCover(true);

        config.setSingleCheck(true);
        //config.addCoverConfig("Sequence", false);
        // config.addCoverConfig("Container", false);
        config.addServiceCoverConfig("Container", false);
        config.addServiceCoverConfig("Sequence", false);
        config.addServiceCoverConfig("Account", false);
         config.addServiceCoverConfig("URIPermission", false);
        // config.getSpecificSpringLocal().add("URIPermission");

        // config.addCriteriaCoverConfig("Role", false);
        config.setGenerateController(false);
        config.setGenerateView(false);
        config.setDefaultCache(true, false, true, false);
        // config.addCoverConfig("URIPermission",true);
        // config.addMapperXmlCoverConfig("Container",true);
        // config.setDefaultCache(true, true, false, false);
        mybatisGenerator.generate("com.senpure.base", config);


    }
}
