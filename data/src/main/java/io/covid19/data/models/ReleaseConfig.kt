package io.covid19.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReleaseConfig(

    @SerializedName("version_name")
    var versionName: String? = null,

    @SerializedName("version_code")
    var versionCode: Int? = null,

    @SerializedName("apk_name")
    var apkName: String? = null,

    @SerializedName("apk_url")
    var apkUrl: String? = null,

    @SerializedName("change_log")
    var changeLog: String? = null
) : Parcelable