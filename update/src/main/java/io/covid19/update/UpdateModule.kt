package io.covid19.update

import dagger.Module
import dagger.Provides

@Module
object UpdateModule {

    @JvmStatic
    @Provides
    fun provideUpdateDownloadManager(context: UpdateActivity): UpdateDownloadManager {
        return UpdateDownloadManager(context)
    }
}