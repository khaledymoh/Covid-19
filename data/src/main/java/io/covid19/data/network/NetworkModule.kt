package io.covid19.data.network

import dagger.Module
import dagger.Provides
import io.covid19.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.droidsonroids.retrofit2.JspoonConverterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {

    @JvmStatic
    @Singleton
    @Provides
    @Named(RETROFIT_WORLDOMETERS_NAME)
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.WORLDOMETERS_BASE_API_URL)
            .addConverterFactory(JspoonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    @Named(RETROFIT_GITHUB_RAW_NAME)
    fun provideGithubRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.GITHUB_RAW_BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideOkHttpLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private const val TIMEOUT = 30L
    const val RETROFIT_WORLDOMETERS_NAME = "WORLDOMETERS"
    const val RETROFIT_GITHUB_RAW_NAME = "GITHUB_RAW"
}