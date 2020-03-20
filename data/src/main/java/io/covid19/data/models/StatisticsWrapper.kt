package io.covid19.data.models

import pl.droidsonroids.jspoon.annotation.Selector

class StatisticsWrapper(

    @Selector("#maincounter-wrap")
    var counter: List<Statistics>? = null,

    @Selector("#main_table_countries_today > tbody:nth-child(3) > tr > td:nth-child(3)")
    var newCases: String? = null,

    @Selector("#main_table_countries_today > tbody:nth-child(3) > tr > td:nth-child(7)")
    var activeCases: String? = null,

    @Selector("#main_table_countries_today > tbody:nth-child(3) > tr > td:nth-child(8)")
    var criticalCases: String? = null,

    @Selector("#main_table_countries_today > tbody:nth-child(3) > tr > td:nth-child(5)")
    var newDeaths: String? = null,

    @Selector("body > div.container > div:nth-child(2) > div.col-md-8 > div > div:nth-child(5)")
    var lastUpdate: String? = null,

    @Selector("body > div.container > div:nth-child(2) > div.col-md-8 > div > div:nth-child(15) > div > div.panel-body > div > div.panel_front > div.number-table-main")
    var closedCases: String? = null,

    @Selector("body > div.container > div:nth-child(2) > div.col-md-8 > div > div:nth-child(14) > div > div.panel-body > div > div.panel_front > div:nth-child(3) > div:nth-child(1) > span")
    var activeInMildCondition: String? = null,

    @Selector("body > div.container > div:nth-child(2) > div.col-md-8 > div > div:nth-child(14) > div > div.panel-body > div > div.panel_front > div:nth-child(3) > div:nth-child(1) > strong")
    var activeInMildConditionPercentage: String? = null,

    @Selector("body > div.container > div:nth-child(2) > div.col-md-8 > div > div:nth-child(14) > div > div.panel-body > div > div.panel_front > div:nth-child(3) > div:nth-child(2) > span")
    var activeCritical: String? = null,

    @Selector("body > div.container > div:nth-child(2) > div.col-md-8 > div > div:nth-child(14) > div > div.panel-body > div > div.panel_front > div:nth-child(3) > div:nth-child(2) > strong")
    var activeCriticalPercentage: String? = null,

    @Selector("body > div.container > div:nth-child(2) > div.col-md-8 > div > div:nth-child(15) > div > div.panel-body > div > div.panel_front > div:nth-child(3) > div:nth-child(1) > span")
    var closedRecovered: String? = null,

    @Selector("body > div.container > div:nth-child(2) > div.col-md-8 > div > div:nth-child(15) > div > div.panel-body > div > div.panel_front > div:nth-child(3) > div:nth-child(1) > strong")
    var closedRecoveredPercentage: String? = null,

    @Selector("body > div.container > div:nth-child(2) > div.col-md-8 > div > div:nth-child(15) > div > div.panel-body > div > div.panel_front > div:nth-child(3) > div:nth-child(2) > span")
    var closedDeaths: String? = null,

    @Selector("body > div.container > div:nth-child(2) > div.col-md-8 > div > div:nth-child(15) > div > div.panel-body > div > div.panel_front > div:nth-child(3) > div:nth-child(2) > strong")
    var closedDeathsPercentage: String? = null,

    @Selector("#main_table_countries_today tr")
    var countries: MutableList<Country>? = null
) {

    fun countriesNames(): MutableList<String> {
        return countries?.filter {
            it.countryName != null
        }?.map {
            it.countryName.orEmpty()
        }?.toMutableList() ?: mutableListOf()
    }


    fun notEmptyCountries(): MutableList<Country> {
        return countries?.filter { it.countryName != null }?.toMutableList() ?: mutableListOf()
    }
}