package com.fx.cloud.common.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fx.cloud.common.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ObjectUtils;

import java.time.Duration;

/**
 * Redis缓存配置
 *
 * @author houcun
 */
@Slf4j
@EnableCaching
@Configuration
@AutoConfigureAfter({RedisAutoConfiguration.class})
public class RedisCacheAutoConfiguration {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.password}")
    private String password;


    @Value("${spring.application.name}")
    private String applicationName;


    public RedisCacheAutoConfiguration() {
        log.info(applicationName + "init redis cache");
    }

    /**
     * 重新配置一个RedisTemplate
     *
     * @param factory
     * @return
     */
    /**
     * 获取连接
     *
     * @return
     */
    @Primary
    @Bean(value = "defaultConnectionFactory")
    public RedisConnectionFactory getDefaultConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisHost);
        configuration.setPort(port);
        configuration.setPassword(password);
        int dbIndex = 0;
        configuration.setDatabase(dbIndex);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration);
        return factory;
    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(getDefaultConnectionFactory());
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    /**
     * 缓存 key 生成规则
     *
     * @return
     */
    @Bean
    public KeyGenerator simpleKeyGenerator() {
        return (o, method, objects) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(method.getName()).append("::");
            stringBuilder.append("[");
            for (Object obj : objects) {
                if (!ObjectUtils.isEmpty(obj)) {
                    stringBuilder.append(obj.toString());
                }
            }
            stringBuilder.append("]");
            return stringBuilder.toString();
        };
    }

    /**
     * 配置缓存管理器
     *
     * @return
     */
    @Bean
    @Primary
    public CacheManager cacheManager() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisHost);
        configuration.setPort(port);
        configuration.setPassword(password);
        int dbIndex = 0;
        try {
            dbIndex = Integer.parseInt(applicationName.substring(8, 10));
            if (dbIndex > 14) {
                dbIndex = 0;
            }
        } catch (NumberFormatException e) {
            dbIndex = 0;
        }
        configuration.setDatabase(dbIndex);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration);
        factory.afterPropertiesSet();
        // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        // 设置缓存的默认过期时间，也是使用Duration设置
        redisCacheConfiguration = redisCacheConfiguration
                // 设置缓存有效期一小时
                .entryTtl(Duration.ofMinutes(8));
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(factory))
                .cacheDefaults(redisCacheConfiguration).build();
    }

    @Bean
    @ConditionalOnMissingBean(RedisUtils.class)
    @ConditionalOnBean(StringRedisTemplate.class)
    public RedisUtils redisUtils(StringRedisTemplate stringRedisTemplate) {
        RedisUtils redisUtils = new RedisUtils(stringRedisTemplate);
        log.info("RedisUtils [{}]", redisUtils);
        return redisUtils;
    }

}
