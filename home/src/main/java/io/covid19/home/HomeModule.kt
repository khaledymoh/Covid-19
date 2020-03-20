package io.covid19.home

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import io.covid19.statistics.StatisticsViewModel
import io.covid19.statistics.StatisticsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

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
            return ViewModelProvider(activity, homeViewModelFactory)[StatisticsViewModel::class.java]
        }
    }

    @Binds
    abstract fun bindActivity(homeActivity: HomeActivity): AppCompatActivity
}