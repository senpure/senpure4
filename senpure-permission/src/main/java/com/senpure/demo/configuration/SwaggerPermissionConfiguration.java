package com.senpure.demo.configuration;

import com.senpure.base.annotation.ExtPermission;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by 罗中正 on 2018/1/15 0015.
 */

@Configuration
public class SwaggerPermissionConfiguration {
    @ExtPermission
    @Component
    class SwaggerPermission {
        @ExtPermission(value = "/v2/api-docs")
        public void apiDoc() {
        }
    }

}
