package com.senpure.base.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 罗中正 on 2017/10/17.
 */
public class LocalRemoteCacheManager extends RedisCacheManager {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private Set<String> localNames = new HashSet<>();

    public LocalRemoteCacheManager(RedisTemplate redisTemplate) {
        super(new DefaultRedisCacheWriter(redisTemplate.getConnectionFactory()), RedisCacheConfiguration.defaultCacheConfig());
    }

    @Override
    protected Cache getMissingCache(String name) {
        logger.debug("getMissingCache {}", name);
        for (String localName : localNames) {
            if (localName.equals(name)) {
                logger.debug("本机缓存 {}", name);
                return new ConcurrentMapCache(name);
            }
        }
        return super.getMissingCache(name);
    }

    public void addLocalName(String name) {
            localNames.add(name);

    }

    public Set<String> getLocalNames() {

        return localNames;
    }
}
