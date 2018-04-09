package io.github.codingsquare.autonut.impl.telegram.bot

import com.mashape.unirest.http.Unirest
import io.github.codingsquare.autonut.Autonut
import io.github.codingsquare.autonut.core.Platform
import io.github.codingsquare.autonut.core.event.MessageReceiveEvent
import io.github.codingsquare.autonut.impl.telegram.core.TelegramMessage
import io.github.codingsquare.autonut.util.logger
import kotlin.concurrent.thread

object TelegramBotPlatform : Platform {

    override val name = "Telegram Bot"

    data class Update(val message: TelegramMessage, val id: Long)

    private fun getUpdates(offset: Long): List<Update> {
        val jsonObject = Unirest.post("https://api.telegram.org/bot${Autonut.TELEGRAM_BOT_TOKEN}/getUpdates")
            .field("offset", offset).asJson().body.`object`
        if (!jsonObject.getBoolean("ok")) {
            logger().debug("Update not received. is it ok?")
        }
        val result = jsonObject.getJSONArray("result")
        return (0 until result.length()).map {
            val update = result.getJSONObject(it)
            Update(TelegramMessage(update.getJSONObject("message")), update.getLong("update_id"))
        }
    }

    override fun start() {
        thread(name = "TelegramBot-LongPolling", isDaemon = true) {
            var offset: Long = 0
            while (true) {
                try {
                    val updates = getUpdates(offset + 1)
                    if (!updates.isEmpty()) {
                        logger().debug("${updates.size} updates received")
                        for (update in updates) {
                            Autonut.post(MessageReceiveEvent(TelegramBotPlatform, update.message))
                            offset = maxOf(offset, update.id)
                        }
                    }
                } catch (e: Throwable) {
                    logger().error(e.message, e)
                }
            }
        }
        logger().info("Telegram bot platform initialized.")
    }
}
