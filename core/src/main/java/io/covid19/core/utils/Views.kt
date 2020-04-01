package io.covid19.core.utils

import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.padding(start: Int = 0, top: Int = 0, end: Int = 0, bottom: Int = 0) {
    this?.setPaddingRelative(start, top, end, bottom)
}

fun AppCompatImageView.setTint(colorRes: Int) {
    this.setColorFilter(
        ContextCompat.getColor(
            context,
            colorRes
        )
    )
}

fun View.smoothRotate(degree: Float) {
    this.animate().rotation(degree).setInterpolator(DecelerateInterpolator()).duration = 250
}