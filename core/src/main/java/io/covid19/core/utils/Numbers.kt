package io.covid19.core.utils

fun String?.toJustInt(): Int {
    if (this.isNullOrEmpty()) {
        return 0
    }
    return this.replace("[^0-9]".toRegex(), "").toInt()
}