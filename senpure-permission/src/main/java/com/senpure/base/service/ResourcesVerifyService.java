package com.senpure.base.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/6.
 */
@Service
public class ResourcesVerifyService {

    private Map<String, ResourceVerifyService> serviceMap = new HashMap<>();
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public void regService(ResourceVerifyService service) {
        Assert.notNull(service.getName(), "name cannot be empty");
        Assert.isNull(serviceMap.get(service.getName()), "ResourceVerify[" + service.getName() + "] is existing !");
        serviceMap.put(service.getName(), service);
    }


    public boolean hasVerifyService(String name)
    {
        return  serviceMap.containsKey(name);
    }
    @Transactional
    public boolean verify(String name, Long accountId, String resourceId) {
        try {
            return serviceMap.get(name).verify(accountId, resourceId);
        } catch (Exception e) {

            logger.error("ERROR", e);
        }
        return false;
    }

    @Transactional
    public List<?> check(String name, Long accountId, List<?> resources) {
        return serviceMap.get(name).check(accountId, resources);
    }

}
