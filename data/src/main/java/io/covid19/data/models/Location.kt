package io.covid19.data.models

import com.google.gson.annotations.SerializedName

data class Location(

    @SerializedName("coordinates")
    val coordinates: Coordinates? = null,

    @SerializedName("country")
    val country: String? = null,

    @SerializedName("country_code")
    val countryCode: String? = null
)