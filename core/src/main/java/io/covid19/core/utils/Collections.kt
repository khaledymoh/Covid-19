package io.covid19.core.utils

fun <T, R : Comparable<R>> MutableList<T>.sortByInverse(inverse : Boolean, selector: (T) -> R) : MutableList<T> {
    return if (inverse) {
        this.sortedByDescending(selector).toMutableList()
    }else {
        this.sortedBy(selector).toMutableList()
    }
}