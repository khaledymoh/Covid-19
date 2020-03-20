package io.covid19.countries

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.covid19.statistics.StatisticsViewModel
import io.covid19.statistics.StatisticsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CountriesModule {

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

        @JvmStatic
        @Provides
        fun provideCountryAdapter(): CountryAdapter {
            return CountryAdapter()
        }
    }

    @Binds
    abstract fun bindFragment(countriesFragment: CountriesFragment): Fragment
}