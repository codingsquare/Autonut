package io.github.codingsquare.autonut.core.event

import io.github.codingsquare.autonut.core.Platform
import io.github.codingsquare.autonut.core.entity.Message

class MessageReceiveEvent(override val platform: Platform, val message: Message) : Event
