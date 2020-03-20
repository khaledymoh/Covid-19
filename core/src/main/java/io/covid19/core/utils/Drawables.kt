package io.covid19.core.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

fun Drawable?.toBitmapDescriptor(width: Int = 24, height: Int = width): BitmapDescriptor {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    this?.setBounds(0, 0, bitmap.width, bitmap.height)
    this?.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}