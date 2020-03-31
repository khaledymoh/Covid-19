package io.covid19.core.utils

import android.view.View

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.padding(start: Int = 0, top: Int = 0, end: Int = 0, bottom: Int = 0) {
    this?.setPaddingRelative(start, top, end, bottom)
}