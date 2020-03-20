package io.covid19.di

import io.covid19.analytics.AnalyticsFragment
import io.covid19.analytics.AnalyticsModule
import io.covid19.countries.CountriesFragment
import io.covid19.countries.CountriesModule
import io.covid19.map.MapFragment
import io.covid19.map.MapModule
import io.covid19.overview.OverviewFragment
import io.covid19.overview.OverviewModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [OverviewModule::class])
    abstract fun bindOverviewFragment(): OverviewFragment

    @ContributesAndroidInjector(modules = [CountriesModule::class])
    abstract fun bindCountriesFragment(): CountriesFragment

    @ContributesAndroidInjector(modules = [MapModule::class])
    abstract fun bindMapFragment(): MapFragment

    @ContributesAndroidInjector(modules = [AnalyticsModule::class])
    abstract fun bindAnalyticsFragment(): AnalyticsFragment
}