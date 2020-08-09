package com.google.shinyay.messaging

import com.google.shinyay.logger
import java.util.concurrent.atomic.AtomicInteger

class Receiver {

    val counter = AtomicInteger()

    fun receiveMessage(message: String) {
        logger.info("Received <$message>")
        counter.incrementAndGet()
    }
}