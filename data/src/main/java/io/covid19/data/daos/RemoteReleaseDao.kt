package io.covid19.data.daos

import io.covid19.data.models.ReleaseConfig
import retrofit2.http.GET

interface RemoteReleaseDao  {

    @GET("release_config.json")
    suspend fun getCurrentReleaseConfig() : ReleaseConfig
}