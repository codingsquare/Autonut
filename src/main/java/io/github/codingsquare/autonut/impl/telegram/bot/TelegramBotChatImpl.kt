package io.github.codingsquare.autonut.impl.telegram.bot

import com.github.salomonbrys.kotson.byLong
import com.github.salomonbrys.kotson.byString
import com.google.gson.JsonElement
import io.github.codingsquare.autonut.impl.telegram.core.TelegramBotChat

class TelegramBotChatImpl(jsonElement: JsonElement) : TelegramBotChat(), TelegramBotMessageChannel {
    override val id: Long by jsonElement.byLong

    private val typeString by jsonElement.byString("type")
    override val type: Type
        get() = when (typeString) {
            "private" -> Type.PRIVATE
            "group" -> Type.GROUP
            "supergroup" -> Type.SUPER_GROUP
            "channel" -> Type.CHANNEL
            else -> Type.UNKNOWN
        }
}
