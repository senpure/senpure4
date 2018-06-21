package com.senpure.base.configuration;

import com.senpure.base.annotation.ExtPermission;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 罗中正 on 2018/1/25 0025.
 */
@Configuration
public class DruidPermissionConfiguration {

    @Component
    @ExtPermission
    class ActuatorPermission {

        @ExtPermission(value = {"/druid","/druid/**.*","/druid/**/*.*"}, name = "/druid_read")
        public void readDruid() {
        }
        @ExtPermission(value = {"/druid/**.*"}, name = "/druid_read",method = RequestMethod.POST)
        public void readDruidJson() {
        }


    }
}
