package io.github.codingsquare.autonut.impl.telegram.bot

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

    fun sendMessage(
        message: String,
        parseMode: ParseMode = ParseMode.UNSET,
        webPagePreview: Boolean = true,
        notify: Boolean = true,
        replyTo: Long? = null
    ): Boolean {
        return TelegramBotPlatform.request("sendMessage").field("chat_id", id).field("text", message)
            .also {
                if (parseMode !== ParseMode.UNSET) {
                    it.field("parse_mode", parseMode.value)
                }
            }.field("disable_web_page_preview", !webPagePreview).field("disable_notification", !notify).also {
                if (replyTo !== null) {
                    it.field("reply_to_message_id", replyTo)
                }
            }.asJson().body.`object`.getBoolean("ok")
    }
}
