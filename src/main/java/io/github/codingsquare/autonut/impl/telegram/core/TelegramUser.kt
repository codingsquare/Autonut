package io.github.codingsquare.autonut.impl.telegram.core

import io.github.codingsquare.autonut.core.entity.User

sealed class TelegramUser : User {
    override val isDirectMessage: Boolean = true
}

abstract class TelegramBotUser : TelegramUser()
