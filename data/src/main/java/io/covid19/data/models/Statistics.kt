package io.covid19.data.models

import pl.droidsonroids.jspoon.annotation.Selector

data class Statistics(

    @Selector(".maincounter-number span")
    var type: String? = null
)