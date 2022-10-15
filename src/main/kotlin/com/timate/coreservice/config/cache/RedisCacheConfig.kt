package com.timate.coreservice.config.cache

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import java.time.Duration


@Configuration
@ConditionalOnProperty("spring.cache.type", havingValue = "redis", matchIfMissing = false)
@ImportAutoConfiguration(CacheAutoConfiguration::class, RedisAutoConfiguration::class)
@EnableCaching
class RedisCacheConfig(
    @Value("\${spring.redis.host:#{'localhost'}}") val redisHost: String,
    @Value("\${spring.redis.port:#{6379}}") val redisPort: Int,
    @Value("\${spring.redis.password:#{null}}") val redisPassword: String?,
) {

    @Bean
    fun redisCacheManager(
        cacheConfiguration: RedisCacheConfiguration
    ): RedisCacheManager {
        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(
            LettuceConnectionFactory(
                RedisStandaloneConfiguration(redisHost, redisPort).apply {
                    if (!redisPassword.isNullOrEmpty()) {
                        setPassword(redisPassword)
                    }
                }
            ).apply { afterPropertiesSet() }
        ).cacheDefaults(cacheConfiguration).build()
    }

    @Bean
    fun cacheConfiguration(): RedisCacheConfiguration {
        val serializer = GenericJackson2JsonRedisSerializer(
            ObjectMapper().apply {
                registerModule(KotlinModule(nullIsSameAsDefault = true))
                registerModule(JavaTimeModule())
                enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY)
            }
        )
        return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(10))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer))
            .prefixCacheNameWith("timate_core-service:")
    }
}