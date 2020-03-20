package io.covid19.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.covid19.data.repositories.statistics.StatisticsRepository
import javax.inject.Inject

class StatisticsViewModelFactory @Inject constructor(
    private val statisticsRepository: StatisticsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StatisticsViewModel(statisticsRepository) as T
    }
}