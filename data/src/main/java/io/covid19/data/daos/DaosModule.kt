package io.covid19.data.daos

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object DaosModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideRemoteStatisticsDao(retrofit: Retrofit): RemoteStatisticsDao {
        return retrofit.create(RemoteStatisticsDao::class.java)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideLocalCountriesDao(context: Context) : LocalCountriesDao {
        return LocalCountriesDao(context)
    }
}