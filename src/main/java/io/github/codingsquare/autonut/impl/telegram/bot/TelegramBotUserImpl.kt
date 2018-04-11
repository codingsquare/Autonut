package io.github.codingsquare.autonut.impl.telegram.bot

import com.github.salomonbrys.kotson.byLong
import com.github.salomonbrys.kotson.byNullableString
import com.github.salomonbrys.kotson.byString
import com.google.gson.JsonElement
import io.github.codingsquare.autonut.impl.telegram.core.TelegramBotUser

class TelegramBotUserImpl(jsonElement: JsonElement) : TelegramBotUser(), TelegramBotMessageChannel {
    val firstName by jsonElement.byString("first_name")
    val lastName by jsonElement.byNullableString("last_name")

    override val displayName: String = firstName + (lastName ?: "")
    override val id: Long by jsonElement.byLong
}
