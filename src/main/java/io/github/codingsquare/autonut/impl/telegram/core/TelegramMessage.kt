package io.github.codingsquare.autonut.impl.telegram.core

import io.github.codingsquare.autonut.core.entity.Message
import io.github.codingsquare.autonut.core.entity.User
import io.github.codingsquare.autonut.impl.telegram.bot.TelegramBotChatImpl
import io.github.codingsquare.autonut.impl.telegram.bot.TelegramBotUserImpl
import org.json.JSONObject

class TelegramMessage(jsonObject: JSONObject) : Message {
    override val text: String? = jsonObject.optString("text")
    override val channel: TelegramChat = TelegramBotChatImpl(jsonObject.getJSONObject("chat"))
    override val from: User? = jsonObject.optJSONObject("from")?.run(::TelegramBotUserImpl)
}
