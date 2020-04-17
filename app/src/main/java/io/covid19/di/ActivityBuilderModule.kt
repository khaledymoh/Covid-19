package io.covid19.di

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.covid19.home.HomeActivity
import io.covid19.home.HomeModule
import io.covid19.update.UpdateActivity
import io.covid19.update.UpdateModule
import io.covid19.update.release.ReleaseAwareModule

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [HomeModule::class, ReleaseAwareModule::class])
    abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [ReleaseAwareModule::class])
    abstract fun bindReleaseAwareActivity(): AppCompatActivity

    @ContributesAndroidInjector(modules = [UpdateModule::class])
    abstract fun bindUpdateActivity(): UpdateActivity
}