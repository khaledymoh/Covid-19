package io.covid19.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.covid19.core.BaseViewModel
import io.covid19.core.utils.combineWith
import io.covid19.data.models.Country
import io.covid19.data.models.StatisticsWrapper
import io.covid19.data.network.Result
import io.covid19.data.repositories.statistics.StatisticsRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class StatisticsViewModel(
    private val statisticsRepository: StatisticsRepository
) : BaseViewModel() {

    private var statisticsResultLiveData = MutableLiveData<Result<StatisticsWrapper>>()
    private var statisticsWrapper: StatisticsWrapper? = null
    private var searchLiveData = MutableLiveData("")

    fun executeRequestStatistics() {
        viewModelScope.launch {
            statisticsResultLiveData.value = Result.Loading(show = true)
            val result = tryResult { statisticsRepository.allStatistics() }
            statisticsResultLiveData.value = Result.Loading(show = false)
            if (result.isSuccess()) {
                statisticsWrapper = (result as Result.Success<StatisticsWrapper>).data
            }
            statisticsResultLiveData.value = result
        }
    }

    fun getLocalCountriesByRemoteLiveData(): LiveData<MutableMap<String, LatLng>> {
        return statisticsResultLiveData.combineWith(statisticsRepository.getLocalCountries()) { remote, local ->
            var countriesLiveData = mutableMapOf<String, LatLng>()
            if (remote is Result.Success) {
                val statisticsCountries = (remote.data as StatisticsWrapper).countriesNames()
                val result = local?.filter {
                    it.key in statisticsCountries
                }?.toMutableMap() ?: mutableMapOf()

                countriesLiveData = result
            }
            countriesLiveData
        }
    }

    fun getTotalCasesByPercentage(country: Country): Int {
        val totalCases = country.totalCases?.replace(",", "")?.toInt()
        return if (totalCases != null) {
            val iconSize = (totalCases * 0.5 / 100).toInt()
            if (iconSize > DEFAULT_TOTAL_CASES_BY_PERCENTAGE) {
                iconSize
            } else {
                DEFAULT_TOTAL_CASES_BY_PERCENTAGE
            }
        } else {
            DEFAULT_TOTAL_CASES_BY_PERCENTAGE
        }
    }

    fun getStatisticsLiveData(): LiveData<Result<StatisticsWrapper>> {
        return statisticsResultLiveData
    }

    fun getCountries(): List<Country> {
        return statisticsWrapper?.countries ?: listOf()
    }

    fun getCountry(countryName: String): Country {
        return getCountries().first { it.countryName == countryName }
    }

    fun searchQuery(query: String) {
        searchLiveData.value = query
    }

    fun getSearchLiveData(): LiveData<String> {
        return searchLiveData
    }

    private companion object {

        private const val DEFAULT_TOTAL_CASES_BY_PERCENTAGE = 50
    }
}