package io.github.codingsquare.autonut.core.entity

interface Message {
    val text: String?
    val channel: MessageChannel
    val from: User?
}
