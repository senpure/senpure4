package com.senpure.base.configuration;

import com.senpure.base.cache.LocalRemoteCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author senpure-generator
 * @version 2018-1-25 18:24:20
 */
@Configuration
@ConditionalOnClass(name = "org.springframework.data.redis.core.RedisTemplate", value = CacheManager.class)
public class LocalCacheConfiguration extends BaseConfiguration {

    private String[] localNames = {"rolePermission", "menu", "roleValue", "permissionMenu", "accountRole", "role", "permission", "systemValue", "accountValue", "containerPermission"};
    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void init() {
        if (cacheManager instanceof LocalRemoteCacheManager) {
            LocalRemoteCacheManager localRemoteCacheManager = (LocalRemoteCacheManager) cacheManager;
            for (String name : localNames) {
                localRemoteCacheManager.addLocalName(name);
            }
        }
    }
}