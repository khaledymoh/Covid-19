package io.covid19.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.covid19.statistics.StatisticsViewModel
import io.covid19.statistics.StatisticsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.covid19.analytics.AnalyticsFragment
import io.covid19.countries.CountriesFragment
import io.covid19.map.MapFragment
import io.covid19.overview.OverviewFragment

@Module
abstract class HomeModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideViewModel(
            activity: HomeActivity,
            homeViewModelFactory: StatisticsViewModelFactory
        ): StatisticsViewModel {
            return ViewModelProvider(
                activity,
                homeViewModelFactory
            )[StatisticsViewModel::class.java]
        }

        @Provides
        @JvmStatic
        fun provideHomePagerAdapter(
            activity: HomeActivity,
            items: MutableMap<Int, Fragment>
        ): HomeViewPagerAdapter {
            return HomeViewPagerAdapter(activity, items.values.toMutableList())
        }

        @Provides
        @JvmStatic
        fun provideAdapterItems(): Map<Int, Fragment> {
            return mutableMapOf(
                R.id.home_bottom_navigation_overview to OverviewFragment(),
                R.id.home_bottom_navigation_countries to CountriesFragment(),
                R.id.home_bottom_navigation_analytics to AnalyticsFragment(),
                R.id.home_bottom_navigation_map to MapFragment()
            )
        }
    }

    @Binds
    abstract fun bindActivity(homeActivity: HomeActivity): AppCompatActivity
}