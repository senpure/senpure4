package com.senpure.base.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.senpure.base.cache.LocalRemoteCacheManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(CacheProperties.class)
@ConditionalOnClass(name = "org.springframework.data.redis.core.RedisTemplate", value = CacheManager.class)
public class LocalRemoteCacheConfiguration {
    @Autowired
    private CacheProperties cacheProperties;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public CacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {

        LocalRemoteCacheManager cacheManager = new LocalRemoteCacheManager(redisTemplate);

        List<String> cacheNames = this.cacheProperties.getCacheNames();
        logger.debug("cacheNames  {}", cacheNames);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // redisTemplate.setValueSerializer(new JDK(getClass().getClassLoader()));
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer());
        logger.debug("redisTemplate key {} ", redisTemplate.getKeySerializer());
        Map<String, Long> expires = new HashMap<>(16);
        expires.put("", 0L);

        return cacheManager;
    }

    private static class StringRedisSerializer implements RedisSerializer {
        private final Charset charset = Charset.forName("utf-8");

        @Override
        public byte[] serialize(Object o) throws SerializationException {

            return o.toString().getBytes(charset);
        }

        @Override
        public Object deserialize(byte[] bytes) throws SerializationException {
            return new String(bytes, this.charset);
        }
    }

    private static class FastJsonRedisSerializer implements RedisSerializer {

        static final byte[] EMPTY_ARRAY = new byte[0];

        @Override
        public byte[] serialize(Object o) throws SerializationException {
            if (o == null) {
                return EMPTY_ARRAY;
            }
            //byte[] bytes = JSON.toJSONBytes(o, SerializerFeature.WriteClassName);
            //System.out.println("put bytes len " + bytes.length);
            return JSON.toJSONBytes(o, SerializerFeature.WriteClassName);
        }

        @Override
        public Object deserialize(byte[] bytes) throws SerializationException {
            if (bytes == null || bytes.length == 0) {
                return null;
            }
            return JSON.parse(bytes);
        }
    }

    private static class JDK extends JdkSerializationRedisSerializer {

        public JDK(ClassLoader classLoader) {
            super(classLoader);
        }

        @Override
        public Object deserialize(byte[] bytes) {
            // System.out.println("get bytes len " + bytes.length);
            return super.deserialize(bytes);
        }

        @Override
        public byte[] serialize(Object object) {
            byte[] bytes = super.serialize(object);
            //System.out.println("put bytes len " + bytes.length);
            return bytes;
        }
    }

}
