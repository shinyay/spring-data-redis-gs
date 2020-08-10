package com.google.shinyay

import com.google.shinyay.messaging.Receiver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.data.redis.core.StringRedisTemplate
import kotlin.system.exitProcess

@SpringBootApplication
class SpringDataRedisGsApplication

fun main(args: Array<String>) {
	val ctx: ApplicationContext = runApplication<SpringDataRedisGsApplication>(*args)
	val redisTemplate = ctx.getBean(StringRedisTemplate::class.java)
	val receiver = ctx.getBean(Receiver::class.java)

	while (receiver.getCount() == 0) {
		redisTemplate.convertAndSend("chat", "Hello from Redis")
		Thread.sleep(500L)
	}
	exitProcess(0)
}


val Any.logger: Logger
	get() = LoggerFactory.getLogger(this.javaClass)