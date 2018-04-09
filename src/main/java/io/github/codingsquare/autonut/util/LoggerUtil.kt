package io.github.codingsquare.autonut.util

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.pattern.color.ANSIConstants
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase
import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T> T.logger(): Logger = LoggerFactory.getLogger(T::class.java)

val rootLogger: Logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)

class ConsoleHighlighter : ForegroundCompositeConverterBase<ILoggingEvent>() {
    override fun getForegroundColorCode(event: ILoggingEvent): String =
        when (event.level) {
            Level.WARN -> ANSIConstants.YELLOW_FG
            Level.ERROR -> ANSIConstants.RED_FG
            Level.DEBUG -> ANSIConstants.CYAN_FG
            Level.TRACE -> ANSIConstants.GREEN_FG
            else -> ANSIConstants.WHITE_FG
        }
}

fun main(args: Array<String>) {
    val numbers = (readLine()!!.toInt() *
            readLine()!!.toInt() *
            readLine()!!.toInt())
        .toString().map { it.toInt() }
    print(numbers)

    for(i in 0..10) {
        println(numbers.filter { it == i }.size)
    }
}
