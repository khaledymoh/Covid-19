package io.covid19.data.daos

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.covid19.data.R
import com.google.android.gms.maps.model.LatLng

class LocalCountriesDao(private val context: Context) {

    private var countriesLiveData = MutableLiveData<MutableMap<String, LatLng>>()
    private var isCountriesAvailable = false

    fun getCountries(): LiveData<MutableMap<String, LatLng>> {
        if (!isCountriesAvailable) {
            extractCountries()
        }
        return countriesLiveData
    }

    private fun extractCountries() {
        val resources = context.resources.openRawResource(R.raw.countries)
        val reader = resources.bufferedReader()
        val countriesMap = mutableMapOf<String, LatLng>()
        reader.useLines { sequence ->
            sequence.forEach { line ->
                val lineAsList = line.split(',')
                val countryName = lineAsList.last()
                val lat = lineAsList[LAT_INDEX]
                val lng = lineAsList[LNG_INDEX]
                if (lat.isNotEmpty() && lng.isNotEmpty()) {
                    val countryLatLng = LatLng(lat.toDouble(), lng.toDouble())
                    countriesMap[countryName] = countryLatLng
                }
            }
        }
        countriesLiveData.value = countriesMap
        isCountriesAvailable = true
    }

    private companion object {

        private const val LAT_INDEX = 1
        private const val LNG_INDEX = 2
    }
}