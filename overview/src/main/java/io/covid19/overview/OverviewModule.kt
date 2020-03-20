package io.covid19.overview

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.covid19.statistics.StatisticsViewModel
import io.covid19.statistics.StatisticsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class OverviewModule {

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
    abstract fun bindFragment(overviewFragment: OverviewFragment): Fragment
}