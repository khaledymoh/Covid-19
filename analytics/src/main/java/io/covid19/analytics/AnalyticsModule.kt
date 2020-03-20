package io.covid19.analytics

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.covid19.statistics.StatisticsViewModel
import io.covid19.statistics.StatisticsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AnalyticsModule  {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideViewModel(
            fragment: Fragment,
            factory: StatisticsViewModelFactory
        ): StatisticsViewModel {
            return ViewModelProvider(fragment.requireActivity(), factory)[StatisticsViewModel::class.java]
        }
    }

    @Binds
    abstract fun bindFragment(analyticsFragment: AnalyticsFragment): Fragment
}