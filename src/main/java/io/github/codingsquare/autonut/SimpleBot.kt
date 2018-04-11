package io.github.codingsquare.autonut

import com.github.salomonbrys.kotson.*
import com.google.common.eventbus.Subscribe
import com.google.gson.JsonElement
import io.github.codingsquare.autonut.core.event.MessageReceiveEvent
import io.github.codingsquare.autonut.util.logger
import io.github.codingsquare.autonut.util.parseJson
import java.io.File
import java.util.concurrent.ThreadLocalRandom

object SimpleBot {
    val secrets = File("secrets.txt").readLines().map { it.trim() }.filter { it.isNotEmpty() && !it.startsWith("#") }
    val updateLogs = File("update_log.json").readText().parseJson().let { json ->
        json.obj.toMap().map { (id, data) ->
            UpdateLog(id, data)
        }
    }.sortedBy { it.id }

    class UpdateLog(val id: String, jsonElement: JsonElement) {
        val version by jsonElement.byString
        private val descriptions by jsonElement.byArray("description")
        val description: String
            get() = descriptions.joinToString("\n") { it.string }
    }

    fun init() {
        Autonut.register(this)
    }

    @Subscribe
    fun onMessageReceived(event: MessageReceiveEvent) {
        val message = event.message
        val channel = message.channel
        message.text?.let {
            logger().debug("${message.from?.displayName ?: "Unknown"}: $it")
            if (!channel.isDirectMessage) {
                // Group message, ignore it.
            }
            when (it) {
                "/도넛의 개인정보" -> {
                    channel.sendMessage(secrets[ThreadLocalRandom.current().nextInt(secrets.size)])
                }
                "/봇 정보" -> {
                    // todo: select version
                    channel.sendMarkdown(updateLogs.last().run { "**현재 버전 : $version**\n```$description```" })
                }
                else -> {
                    // do nothing
                }
            }
        }
    }
}
