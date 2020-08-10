package com.google.shinyay.configuration

import com.google.shinyay.messaging.Receiver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter

@Configuration
class RedisConfiguration {

    @Bean
    fun receiver() = Receiver()

    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory)
            = StringRedisTemplate(connectionFactory)

    @Bean
    fun listenerAdapter(receiver: Receiver)
            = MessageListenerAdapter(receiver, "receiveMessage")

    @Bean
    fun listenerContainer(connectionFactory: RedisConnectionFactory,
                          listenerAdapter: MessageListenerAdapter): RedisMessageListenerContainer {
        return RedisMessageListenerContainer().apply {
            setConnectionFactory(connectionFactory)
            addMessageListener(listenerAdapter, PatternTopic("chat"))
        }
    }
}