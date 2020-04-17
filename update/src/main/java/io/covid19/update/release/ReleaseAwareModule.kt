package io.covid19.update.release

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides

@Module
abstract class ReleaseAwareModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideReleaseAwareViewModel(
            activity: AppCompatActivity,
            updateViewModelFactory: ReleaseAwareViewModelFactory
        ): ReleaseAwareViewModel {
            return ViewModelProvider(
                activity,
                updateViewModelFactory
            )[ReleaseAwareViewModel::class.java]
        }
    }
}