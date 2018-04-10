package io.github.codingsquare.autonut.impl.telegram.bot

import io.github.codingsquare.autonut.impl.telegram.core.TelegramBotChat
import org.json.JSONObject

class TelegramBotChatImpl(jsonObject: JSONObject) : TelegramBotChat(), TelegramBotMessageChannel {
    override val id: Long = jsonObject.getLong("id")

    override val type: Type = when (jsonObject.getString("type")) {
        "private" -> Type.PRIVATE
        "group" -> Type.GROUP
        "supergroup" -> Type.SUPER_GROUP
        "channel" -> Type.CHANNEL
        else -> Type.UNKNOWN
    }
}
