package io.github.codingsquare.autonut.impl.telegram.bot

import com.github.salomonbrys.kotson.byLong
import com.github.salomonbrys.kotson.byNullableString
import com.github.salomonbrys.kotson.byString
import com.google.gson.JsonObject
import io.github.codingsquare.autonut.impl.telegram.core.TelegramBotUser

class TelegramBotUserImpl(jsonObject: JsonObject) : TelegramBotUser(), TelegramBotMessageChannel {
    val firstName by jsonObject.byString("first_name")
    val lastName by jsonObject.byNullableString("last_name")

    override val displayName: String = firstName + (lastName ?: "")
    override val id: Long by jsonObject.byLong
}
