package io.covid19.core.utils

import android.view.View

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.hide() {
    this?.visibility = View.INVISIBLE
}