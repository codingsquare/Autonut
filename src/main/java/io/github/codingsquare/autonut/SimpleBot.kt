package io.github.codingsquare.autonut

import com.google.common.eventbus.Subscribe
import io.github.codingsquare.autonut.core.event.MessageReceiveEvent
import io.github.codingsquare.autonut.util.logger

object SimpleBot {
    fun init() {
        Autonut.register(this)
    }

    @Subscribe
    fun onMessageReceived(event: MessageReceiveEvent) {
        event.message.text?.let {
            logger().info("Test: $it")
        }
    }
}
