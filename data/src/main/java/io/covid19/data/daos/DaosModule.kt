package io.covid19.data.daos

import android.content.Context
import dagger.Module
import dagger.Provides
import io.covid19.data.network.NetworkModule.RETROFIT_GITHUB_RAW_NAME
import io.covid19.data.network.NetworkModule.RETROFIT_WORLDOMETERS_NAME
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
object DaosModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideRemoteStatisticsDao(
        @Named(RETROFIT_WORLDOMETERS_NAME) retrofit: Retrofit
    ): RemoteStatisticsDao {
        return retrofit.create(RemoteStatisticsDao::class.java)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRemoteReleaseDao(
        @Named(RETROFIT_GITHUB_RAW_NAME) retrofit: Retrofit
    ): RemoteReleaseDao {
        return retrofit.create(RemoteReleaseDao::class.java)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideLocalCountriesDao(context: Context): LocalCountriesDao {
        return LocalCountriesDao(context)
    }
}