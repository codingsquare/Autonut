package io.github.codingsquare.autonut.impl.telegram.bot

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getOrElse
import com.github.kittinunf.result.map
import com.github.salomonbrys.kotson.array
import com.github.salomonbrys.kotson.bool
import com.github.salomonbrys.kotson.byLong
import com.github.salomonbrys.kotson.get
import com.google.gson.JsonElement
import io.github.codingsquare.autonut.Autonut
import io.github.codingsquare.autonut.core.Platform
import io.github.codingsquare.autonut.core.event.MessageReceiveEvent
import io.github.codingsquare.autonut.impl.telegram.core.TelegramMessage
import io.github.codingsquare.autonut.util.byClass
import io.github.codingsquare.autonut.util.logger
import io.github.codingsquare.autonut.util.parseJson
import kotlin.concurrent.thread

object TelegramBotPlatform : Platform {
    override val name = "Telegram Bot"

    class Update(jsonElement: JsonElement) {
        val id by jsonElement.byLong("update_id")
        val message: TelegramMessage by jsonElement.byClass()
    }

    private fun getUpdates(offset: Long): List<Update> {
        return request("getUpdates", listOf("offset" to offset)).map { jsonObject ->
            if (!jsonObject["ok"].bool) {
                logger().debug("Update not received. is it ok?")
            }
            val result = jsonObject["result"].array
            result.map(::Update)
        }.getOrElse(emptyList())
    }

    fun request(method: String, parameters: List<Pair<String, Any?>>): Result<JsonElement, FuelError> =
        "https://api.telegram.org/bot${Autonut.TELEGRAM_BOT_TOKEN}/$method".httpPost(parameters).response().third.map {
            it.toString().parseJson()
        }

    override fun start() {
        thread(name = "TelegramBot-LongPolling", isDaemon = true) {
            var offset: Long = 0
            while (true) {
                try {
                    val updates =
                        getUpdates(offset + 1)
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
