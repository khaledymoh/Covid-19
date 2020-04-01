package io.covid19.countries

import android.content.Context
import io.covid19.core.utils.sortByInverse
import io.covid19.core.utils.toJustInt
import io.covid19.data.models.Country

object CountriesItemsFactory {

    var isTotalCasesSorted = true
    var isNewCasesSorted = false
    var isTotalDeathsSorted = false
    var isNewDeathsSorted = false
    var isTotalRecoveredSorted = false
    var isActiveCasesSorted = false
    var isCriticalCasesSorted = false

    var isRotated = false

    fun getSortedItems(
        context: Context,
        tag: String,
        items: MutableList<Country>
    ): MutableList<Country> {
        return when (tag) {
            context.getString(R.string.label_statistics_total_cases) -> {
                isTotalCasesSorted = !isTotalCasesSorted
                isRotated = !isTotalCasesSorted
                items.sortByInverse(isTotalCasesSorted) { it.totalCases.toJustInt() }
            }
            context.getString(R.string.label_statistics_new_cases) -> {
                isNewCasesSorted = !isNewCasesSorted
                isRotated = !isNewCasesSorted
                items.sortByInverse(isNewCasesSorted) { it.newCases.toJustInt() }
            }
            context.getString(R.string.label_statistics_total_deaths) -> {
                isTotalDeathsSorted = !isTotalDeathsSorted
                isRotated = !isTotalDeathsSorted
                items.sortByInverse(isTotalDeathsSorted) { it.totalDeaths.toJustInt() }
            }
            context.getString(R.string.label_statistics_new_deaths) -> {
                isNewDeathsSorted = !isNewDeathsSorted
                isRotated = !isNewDeathsSorted
                items.sortByInverse(isNewDeathsSorted) { it.newDeaths.toJustInt() }
            }
            context.getString(R.string.label_statistics_total_recovered) -> {
                isTotalRecoveredSorted = !isTotalRecoveredSorted
                isRotated = !isTotalRecoveredSorted
                items.sortByInverse(isTotalRecoveredSorted) { it.totalRecovered.toJustInt() }
            }
            context.getString(R.string.label_statistics_active_cases) -> {
                isActiveCasesSorted = !isActiveCasesSorted
                isRotated = !isActiveCasesSorted
                items.sortByInverse(isActiveCasesSorted) { it.activeCases.toJustInt() }
            }
            context.getString(R.string.label_statistics_critical_cases) -> {
                isCriticalCasesSorted = !isCriticalCasesSorted
                isRotated = !isCriticalCasesSorted
                items.sortByInverse(isCriticalCasesSorted) { it.criticalCases.toJustInt() }
            }
            else -> {
                items.sortedByDescending { it.totalCases.toJustInt() }.toMutableList()
            }
        }
    }
}