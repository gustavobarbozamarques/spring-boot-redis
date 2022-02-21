package br.com.gustavobarbozamarques.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.time.Duration;

import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;
import static org.springframework.data.redis.cache.RedisCacheWriter.nonLockingRedisCacheWriter;
import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        var jsonSerializer = new Jackson2JsonRedisSerializer(Object.class);
        var objectMapper = new ObjectMapper();
        jsonSerializer.setObjectMapper(objectMapper);

        return RedisCacheManagerBuilder
                .fromCacheWriter(nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory()))
                .withCacheConfiguration("books",
                        defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(10))
                                .serializeValuesWith(fromSerializer(jsonSerializer))
                ).build();
    }
}
