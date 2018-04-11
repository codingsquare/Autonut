package io.github.codingsquare.autonut.impl.telegram.core

import com.github.salomonbrys.kotson.byNullableString
import com.google.gson.JsonObject
import io.github.codingsquare.autonut.core.entity.Message
import io.github.codingsquare.autonut.core.entity.User
import io.github.codingsquare.autonut.impl.telegram.bot.TelegramBotChatImpl
import io.github.codingsquare.autonut.util.byClass
import io.github.codingsquare.autonut.util.byNullableClass

class TelegramMessage(jsonObject: JsonObject) : Message {
    override val text: String? by jsonObject.byNullableString("text")
    override val channel: TelegramChat by jsonObject.byClass<TelegramBotChatImpl>("chat")
    override val from: User? by jsonObject.byNullableClass()
}
