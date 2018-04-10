package io.github.codingsquare.autonut.core.entity

interface MessageChannel {
    val isDirectMessage: Boolean

    fun sendMessage(message: String): Boolean
}
