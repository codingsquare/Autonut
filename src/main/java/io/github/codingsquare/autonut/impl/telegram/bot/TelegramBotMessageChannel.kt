package io.github.codingsquare.autonut.impl.telegram.bot

import com.github.kittinunf.result.getOrElse
import com.github.kittinunf.result.map
import com.github.salomonbrys.kotson.bool
import com.github.salomonbrys.kotson.get
import io.github.codingsquare.autonut.core.entity.MessageChannel

interface TelegramBotMessageChannel : MessageChannel {
    enum class ParseMode(val value: String) {
        MARKDOWN("Markdown"),
        HTML("HTML"),
        UNSET("")
    }

    val id: Long

    override fun sendMessage(message: String): Boolean {
        return sendMessage(message, ParseMode.UNSET)
    }

    override fun sendMarkdown(message: String): Boolean {
        return sendMessage(message, ParseMode.MARKDOWN)
    }


    fun sendMessage(
        message: String,
        parseMode: ParseMode = ParseMode.UNSET,
        webPagePreview: Boolean = true,
        notify: Boolean = true,
        replyTo: Long? = null
    ): Boolean {
        return TelegramBotPlatform.request(
            "sendMessage",
            listOf(
                "chat_id" to id,
                "text" to message,
                "parse_mode" to parseMode.value,
                "disable_web_page_preview" to !webPagePreview,
                "disable_notification" to !notify,
                "reply_to_message_id" to replyTo
            )
        ).map { it["ok"].bool == true }.getOrElse(false)
    }
}
