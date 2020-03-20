package io.covid19.map

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.covid19.statistics.StatisticsViewModel
import io.covid19.statistics.StatisticsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MapModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideViewModel(
            fragment: MapFragment,
            homeViewModelFactory: StatisticsViewModelFactory
        ): StatisticsViewModel {
            return ViewModelProvider(fragment.requireActivity(), homeViewModelFactory)[StatisticsViewModel::class.java]
        }

        @Provides
        @JvmStatic
        fun provideCountriesMapInfoWindowAdapter(
            fragment: MapFragment
        ): CountriesMapInfoWindowAdapter {
            return CountriesMapInfoWindowAdapter(fragment.requireActivity())
        }
    }

    @Binds
    abstract fun bindFragment(mapFragment: MapFragment): Fragment
}