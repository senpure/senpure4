package com.senpure.base.cache;


import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.*;

/**
 * 复制于spring data redis 重写了getMissCache()
 */
public class RedisCacheManager extends AbstractTransactionSupportingCacheManager {

    private final RedisCacheWriter cacheWriter;
    private final RedisCacheConfiguration defaultCacheConfig;
    private final Map<String, RedisCacheConfiguration> initialCacheConfiguration;
    private final boolean allowInFlightCacheCreation;

    private RedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
                              boolean allowInFlightCacheCreation) {

        Assert.notNull(cacheWriter, "CacheWriter must not be null!");
        Assert.notNull(defaultCacheConfiguration, "DefaultCacheConfiguration must not be null!");

        this.cacheWriter = cacheWriter;
        this.defaultCacheConfig = defaultCacheConfiguration;
        this.initialCacheConfiguration = new LinkedHashMap<>();
        this.allowInFlightCacheCreation = allowInFlightCacheCreation;
    }


    public RedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        this(cacheWriter, defaultCacheConfiguration, true);
    }


    public RedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
                             String... initialCacheNames) {

        this(cacheWriter, defaultCacheConfiguration, true, initialCacheNames);
    }


    public RedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
                             boolean allowInFlightCacheCreation, String... initialCacheNames) {

        this(cacheWriter, defaultCacheConfiguration, allowInFlightCacheCreation);

        for (String cacheName : initialCacheNames) {
            this.initialCacheConfiguration.put(cacheName, defaultCacheConfiguration);
        }
    }


    public RedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
                             Map<String, RedisCacheConfiguration> initialCacheConfigurations) {

        this(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations, true);
    }


    public RedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
                             Map<String, RedisCacheConfiguration> initialCacheConfigurations, boolean allowInFlightCacheCreation) {

        this(cacheWriter, defaultCacheConfiguration, allowInFlightCacheCreation);

        Assert.notNull(initialCacheConfigurations, "InitialCacheConfigurations must not be null!");

        this.initialCacheConfiguration.putAll(initialCacheConfigurations);
    }


    public static RedisCacheManager create(RedisConnectionFactory connectionFactory) {

        Assert.notNull(connectionFactory, "ConnectionFactory must not be null!");

        return new RedisCacheManager(new DefaultRedisCacheWriter(connectionFactory),
                RedisCacheConfiguration.defaultCacheConfig());
    }


    public static LocalRemoteCacheManagerBuilder builder(RedisConnectionFactory connectionFactory) {

        Assert.notNull(connectionFactory, "ConnectionFactory must not be null!");

        return LocalRemoteCacheManagerBuilder.fromConnectionFactory(connectionFactory);
    }


    public static LocalRemoteCacheManagerBuilder builder(RedisCacheWriter cacheWriter) {

        Assert.notNull(cacheWriter, "CacheWriter must not be null!");

        return LocalRemoteCacheManagerBuilder.fromCacheWriter(cacheWriter);
    }


    @Override
    protected Collection<RedisCache> loadCaches() {

        List<RedisCache> caches = new LinkedList<>();

        for (Map.Entry<String, RedisCacheConfiguration> entry : initialCacheConfiguration.entrySet()) {
            caches.add(createRedisCache(entry.getKey(), entry.getValue()));
        }

        return caches;
    }


    @Override
    protected Cache getMissingCache(String name) {
        return allowInFlightCacheCreation ? createRedisCache(name, defaultCacheConfig) : null;
    }


    public Map<String, RedisCacheConfiguration> getCacheConfigurations() {

        Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>(getCacheNames().size());

        getCacheNames().forEach(it -> {

            RedisCache cache = RedisCache.class.cast(lookupCache(it));
            configurationMap.put(it, cache != null ? cache.getCacheConfiguration() : null);
        });

        return Collections.unmodifiableMap(configurationMap);
    }


    protected RedisCache createRedisCache(String name, @Nullable RedisCacheConfiguration cacheConfig) {
        return new RedisCache(name, cacheWriter, cacheConfig != null ? cacheConfig : defaultCacheConfig);
    }


    public static class LocalRemoteCacheManagerBuilder {

        private final RedisCacheWriter cacheWriter;
        private RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        private final Map<String, RedisCacheConfiguration> initialCaches = new LinkedHashMap<>();
        private boolean enableTransactions;
        boolean allowInFlightCacheCreation = true;

        private LocalRemoteCacheManagerBuilder(RedisCacheWriter cacheWriter) {
            this.cacheWriter = cacheWriter;
        }


        public static LocalRemoteCacheManagerBuilder fromConnectionFactory(RedisConnectionFactory connectionFactory) {

            Assert.notNull(connectionFactory, "ConnectionFactory must not be null!");

            return builder(new DefaultRedisCacheWriter(connectionFactory));
        }


        public static LocalRemoteCacheManagerBuilder fromCacheWriter(RedisCacheWriter cacheWriter) {

            Assert.notNull(cacheWriter, "CacheWriter must not be null!");

            return new LocalRemoteCacheManagerBuilder(cacheWriter);
        }


        public LocalRemoteCacheManagerBuilder cacheDefaults(RedisCacheConfiguration defaultCacheConfiguration) {

            Assert.notNull(defaultCacheConfiguration, "DefaultCacheConfiguration must not be null!");

            this.defaultCacheConfiguration = defaultCacheConfiguration;

            return this;
        }


        public LocalRemoteCacheManagerBuilder transactionAware() {

            this.enableTransactions = true;

            return this;
        }


        public LocalRemoteCacheManagerBuilder initialCacheNames(Set<String> cacheNames) {

            Assert.notNull(cacheNames, "CacheNames must not be null!");

            Map<String, RedisCacheConfiguration> cacheConfigMap = new LinkedHashMap<>(cacheNames.size());
            cacheNames.forEach(it -> cacheConfigMap.put(it, defaultCacheConfiguration));

            return withInitialCacheConfigurations(cacheConfigMap);
        }


        public LocalRemoteCacheManagerBuilder withInitialCacheConfigurations(
                Map<String, RedisCacheConfiguration> cacheConfigurations) {

            Assert.notNull(cacheConfigurations, "CacheConfigurations must not be null!");
            cacheConfigurations.forEach((cacheName, configuration) -> Assert.notNull(configuration,
                    String.format("RedisCacheConfiguration for cache %s must not be null!", cacheName)));

            this.initialCaches.putAll(cacheConfigurations);

            return this;
        }


        public LocalRemoteCacheManagerBuilder disableCreateOnMissingCache() {

            this.allowInFlightCacheCreation = false;
            return this;
        }


        public RedisCacheManager build() {

            RedisCacheManager cm = new RedisCacheManager(cacheWriter, defaultCacheConfiguration, initialCaches,
                    allowInFlightCacheCreation);

            cm.setTransactionAware(enableTransactions);

            return cm;
        }
    }
}
