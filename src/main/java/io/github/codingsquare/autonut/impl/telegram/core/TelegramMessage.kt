package io.github.codingsquare.autonut.impl.telegram.core

import io.github.codingsquare.autonut.core.entity.Message
import org.json.JSONObject

class TelegramMessage(jsonObject: JSONObject) : Message {
    override val text: String? = jsonObject.getString("text")
}
