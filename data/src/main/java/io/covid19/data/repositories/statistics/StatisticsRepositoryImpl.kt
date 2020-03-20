package io.covid19.data.repositories.statistics

import androidx.lifecycle.LiveData
import io.covid19.data.daos.LocalCountriesDao
import io.covid19.data.daos.RemoteStatisticsDao
import io.covid19.data.models.StatisticsWrapper
import com.google.android.gms.maps.model.LatLng

class StatisticsRepositoryImpl(
    private val remoteStatisticsDao: RemoteStatisticsDao,
    private val localCountriesDao: LocalCountriesDao
) : StatisticsRepository {

    override suspend fun allStatistics(): StatisticsWrapper {
        return remoteStatisticsDao.getAllStatistics()
    }

    override fun getLocalCountries(): LiveData<MutableMap<String, LatLng>> {
        return localCountriesDao.getCountries()
    }
}