package io.covid19.data.models

import pl.droidsonroids.jspoon.annotation.Selector

data class Country(

    @Selector("td:nth-child(1)")
    var countryName: String? = null,

    @Selector("td:nth-child(2)")
    var totalCases: String? = null,

    @Selector("td:nth-child(3)")
    var newCases: String? = null,

    @Selector("td:nth-child(4)")
    var totalDeaths: String? = null,

    @Selector("td:nth-child(5)")
    var newDeaths: String? = null,

    @Selector("td:nth-child(6)")
    var totalRecovered: String? = null,

    @Selector("td:nth-child(7)")
    var activeCases: String? = null,

    @Selector("td:nth-child(8)")
    var criticalCases: String? = null,

    @Selector("td:nth-child(13)")
    var continentName: String? = null
)