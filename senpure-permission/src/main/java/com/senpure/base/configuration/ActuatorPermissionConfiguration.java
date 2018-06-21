package com.senpure.base.configuration;

import com.senpure.base.annotation.ExtPermission;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 罗中正 on 2018/1/25 0025.
 */
@Configuration
public class ActuatorPermissionConfiguration {


    @Component
    @ExtPermission
    class ActuatorPermission {

        @ExtPermission(value ={"/actuator/auditevents","/actuator/auditevents.json"},name = "/actuator_read")
        public void readAuditevents(){
        }
        @ExtPermission(value ={"/actuator/beans","/actuator/beans.json"},name = "/actuator_read")
        public void readBeans(){
        }
        @ExtPermission(value ={"/actuator/health","/actuator/health.json"},name = "/actuator_read")
        public void readHealth(){
        }
        @ExtPermission(value ={"/actuator/conditions","/actuator/conditions.json"},name = "/actuator_read")
        public void readConditions(){
        }

        @ExtPermission(value ={"/actuator/configprops","/actuator/configprops.json"},name = "/actuator_read")
        public void readConfigprops(){
        }
        @ExtPermission(value ={"/actuator/env","/actuator/env.json","/actuator/env/{toMatch:.*}"},name = "/actuator_read")
        public void readEnv(){
        }
        @ExtPermission(value ={"/actuator/info","/actuator/info.json"},name = "/actuator_read")
        public void readInfo(){
        }
        @ExtPermission(value ={"/actuator/loggers","/actuator/loggers.json","/actuator/loggers/{name:.*}"},name = "/actuator_read")
        public void readLoggers(){
        }
        @ExtPermission(value ={"/actuator/heapdump","/actuator/heapdump.json"},name = "/actuator_read")
        public void readHeapdump(){
        }
        @ExtPermission(value ={"/actuator/threaddump","/actuator/threaddump.json"},name = "/actuator_read")
        public void readThreaddump(){
        }
        @ExtPermission(value ={"/actuator/metrics","/actuator/metrics.json","/actuator/metrics/{requiredMetricName}"},name = "/actuator_read")
        public void readMetrics(){
        }
        @ExtPermission(value ={"/actuator/scheduledtasks","/actuator/scheduledtasks.json"},name = "/actuator_read")
        public void readScheduledtasks(){
        }
        @ExtPermission(value ={"/actuator/httptrace","/actuator/httptrace.json"},name = "/actuator_read")
        public void readHttptrace(){
        }
        @ExtPermission(value ={"/actuator/mappings","/actuator/mappings.json"},name = "/actuator_read")
        public void readMappings(){
        }

        @ExtPermission(value = {"/actuator/loggers/{name:.*}"}, name = "/actuator_update",method = RequestMethod.POST)
        public void updateLoggers() {
        }

        @ExtPermission(value ={"/actuator/shutdown"},name = "/actuator_update",method = RequestMethod.POST)
        public void updateShutdown(){
        }

    }
}
