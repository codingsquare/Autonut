package io.github.codingsquare.autonut.impl.telegram.bot

import io.github.codingsquare.autonut.impl.telegram.core.TelegramBotUser
import org.json.JSONObject

class TelegramBotUserImpl(jsonObject: JSONObject) : TelegramBotUser(), TelegramBotMessageChannel {
    override val displayName: String = jsonObject.getString("first_name") + (jsonObject.optString("last_name") ?: "")
    override val id: Long = jsonObject.getLong("id")
}
