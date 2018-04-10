package io.github.codingsquare.autonut

import com.google.common.eventbus.Subscribe
import io.github.codingsquare.autonut.core.event.MessageReceiveEvent
import io.github.codingsquare.autonut.util.logger
import java.io.File
import java.util.concurrent.ThreadLocalRandom

object SimpleBot {
    val secrets = File("secrets.txt").readLines().filter { it.isNotEmpty() }
    fun init() {
        Autonut.register(this)
    }

    @Subscribe
    fun onMessageReceived(event: MessageReceiveEvent) {
        event.message.text?.let {
            logger().debug("${event.message.from?.displayName ?: "Unknown"}: $it")
            if (event.message.channel.isDirectMessage && it == "/도넛의 개인정보") {
                event.message.channel.sendMessage(secrets[ThreadLocalRandom.current().nextInt(secrets.size)])
            }
        }
    }
}
