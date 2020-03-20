package io.covid19.data.repositories.statistics

import androidx.lifecycle.LiveData
import io.covid19.data.models.StatisticsWrapper
import com.google.android.gms.maps.model.LatLng

interface StatisticsRepository {

    suspend fun allStatistics(): StatisticsWrapper

    fun getLocalCountries() : LiveData<MutableMap<String, LatLng>>
}