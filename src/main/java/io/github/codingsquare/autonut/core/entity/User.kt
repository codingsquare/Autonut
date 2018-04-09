package io.github.codingsquare.autonut.core.entity

import io.github.codingsquare.autonut.core.Platform

interface User {
    val displayName: String
    val platform: Platform

    fun sendMessage(message: Message)
}
