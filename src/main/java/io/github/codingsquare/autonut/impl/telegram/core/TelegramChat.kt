package io.github.codingsquare.autonut.impl.telegram.core

import io.github.codingsquare.autonut.core.entity.MessageChannel

sealed class TelegramChat : MessageChannel {
    enum class Type {
        PRIVATE,
        GROUP,
        SUPER_GROUP,
        CHANNEL,
        UNKNOWN
    }

    abstract val type: Type

    override val isDirectMessage: Boolean
        get() = type == Type.PRIVATE
}

abstract class TelegramBotChat internal constructor() : TelegramChat()
