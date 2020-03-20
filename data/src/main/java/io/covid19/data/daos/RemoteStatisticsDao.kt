package io.covid19.data.daos

import io.covid19.data.models.StatisticsWrapper
import retrofit2.http.GET

interface RemoteStatisticsDao {

    @GET("coronavirus")
    suspend fun getAllStatistics(): StatisticsWrapper
}