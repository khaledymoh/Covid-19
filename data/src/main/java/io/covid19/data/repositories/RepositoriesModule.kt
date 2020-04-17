package io.covid19.data.repositories

import io.covid19.data.daos.LocalCountriesDao
import io.covid19.data.daos.RemoteStatisticsDao
import io.covid19.data.repositories.statistics.StatisticsRepository
import io.covid19.data.repositories.statistics.StatisticsRepositoryImpl
import dagger.Module
import dagger.Provides
import io.covid19.data.daos.RemoteReleaseDao
import io.covid19.data.repositories.release.ReleaseRepository
import io.covid19.data.repositories.release.ReleaseRepositoryImpl
import javax.inject.Singleton

@Module
object RepositoriesModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideStatisticsRepository(
        remoteStatisticsDao: RemoteStatisticsDao,
        localCountriesDao: LocalCountriesDao
    ): StatisticsRepository {
        return StatisticsRepositoryImpl(remoteStatisticsDao, localCountriesDao)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideReleaseRepository(remoteReleaseDao: RemoteReleaseDao): ReleaseRepository {
        return ReleaseRepositoryImpl(remoteReleaseDao)
    }
}