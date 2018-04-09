package io.github.codingsquare.autonut

import io.github.codingsquare.autonut.core.Platform
import io.github.codingsquare.autonut.impl.telegram.bot.TelegramBotPlatform
import io.github.codingsquare.autonut.util.rootLogger
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    rootLogger.info("Autonut has been started.")

    val platforms = mutableListOf<Platform>()

    if (Autonut.TELEGRAM_BOT_TOKEN !== null) {
        rootLogger.info("Telegram bot token found.")

        platforms += TelegramBotPlatform
    } else {
        rootLogger.warn("Telegram bot token not found, set `AUTONUT_TELEGRAM_BOT_TOKEN` in environment variable!")
    }

    if (platforms.isEmpty()) {
        rootLogger.warn("No platform found, program exiting...")
        exitProcess(0)
    }
    platforms.forEach {
        it.start()
    }

    SimpleBot.init()

    rootLogger.info("Press a key to exit.")
    readLine()
}
