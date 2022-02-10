package co.elastic.android.gradle

import org.slf4j.Logger

fun Logger.warn(throwable: Throwable? = null, message: () -> String) {
    warn("[elastic] ${message()}", throwable)
}

fun Logger.error(throwable: Throwable? = null, message: () -> String) {
    error("[elastic] ${message()}", throwable)
}

fun Logger.debug(throwable: Throwable? = null, message: () -> String) {
    debug("[elastic] ${message()}", throwable)
}

fun Logger.info(throwable: Throwable? = null, message: () -> String) {
    info("[elastic] ${message()}", throwable)
}
