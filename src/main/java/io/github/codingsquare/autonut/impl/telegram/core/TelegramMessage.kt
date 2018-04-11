package io.github.codingsquare.autonut.impl.telegram.core

import com.github.salomonbrys.kotson.byNullableString
import com.google.gson.JsonElement
import io.github.codingsquare.autonut.core.entity.Message
import io.github.codingsquare.autonut.impl.telegram.bot.TelegramBotChatImpl
import io.github.codingsquare.autonut.impl.telegram.bot.TelegramBotUserImpl
import io.github.codingsquare.autonut.util.byClass
import io.github.codingsquare.autonut.util.byNullableClass

class TelegramMessage(jsonElement: JsonElement) : Message {
    override val text: String? by jsonElement.byNullableString("text")
    override val channel: TelegramChat by jsonElement.byClass("chat", getter = { TelegramBotChatImpl(it) })
    override val from: TelegramBotUser? by jsonElement.byNullableClass(getter = { TelegramBotUserImpl(it) })
}
