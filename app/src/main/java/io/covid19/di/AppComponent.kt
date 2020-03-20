package io.covid19.di

import android.content.Context
import io.covid19.App
import io.covid19.data.daos.DaosModule
import io.covid19.data.network.NetworkModule
import io.covid19.data.repositories.RepositoriesModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class,
        RepositoriesModule::class,
        NetworkModule::class,
        DaosModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance app: App, @BindsInstance context: Context): AppComponent
    }
}