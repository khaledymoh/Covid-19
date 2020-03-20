package io.covid19.core.utils

import com.google.gson.Gson

fun Any.toJson(): String {
    return Gson().toJson(this, this::class.java)
}

inline fun <reified T> String.toObject(): T {
    return Gson().fromJson(this, T::class.java)
}