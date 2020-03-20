package io.covid19.data.models

import com.google.gson.annotations.SerializedName

data class SimpleStatistics(
    @SerializedName("confirmed")
    val confirmed: Int? = null,

    @SerializedName("deaths")
    val deaths: Int? = null,

    @SerializedName("recovered")
    val recovered: Int? = null
)