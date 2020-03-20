package io.covid19.data.models

import com.google.gson.annotations.SerializedName

data class Coordinates(

    @SerializedName("lat")
    val lat: String? = null,

    @SerializedName("long")
    val long: String? = null
)