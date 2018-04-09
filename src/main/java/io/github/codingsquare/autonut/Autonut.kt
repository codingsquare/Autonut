package io.github.codingsquare.autonut

import com.google.common.eventbus.EventBus
import io.github.codingsquare.autonut.core.event.Event

object Autonut {
    val TELEGRAM_BOT_TOKEN = System.getenv("AUTONUT_TELEGRAM_BOT_TOKEN")

    private val bus = EventBus()

    fun post(event: Event) {
        bus.post(event)
    }

    fun register(listener: Any) {
        bus.register(listener)
    }

    fun unregister(listener: Any) {
        bus.unregister(listener)
    }
}
