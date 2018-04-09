package io.github.codingsquare.autonut.impl.telegram.bot

import io.github.codingsquare.autonut.core.entity.Message
import io.github.codingsquare.autonut.core.entity.User

class TelegramBotUser(val id: Long, override val displayName: String) : User {
    override val platform = TelegramBotPlatform

    override fun sendMessage(message: Message) {

    }
}
