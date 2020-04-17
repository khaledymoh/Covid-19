package io.covid19.update.release

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.covid19.data.repositories.release.ReleaseRepository
import javax.inject.Inject

class ReleaseAwareViewModelFactory @Inject constructor(
    private val releaseRepository: ReleaseRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReleaseAwareViewModel(releaseRepository) as T
    }
}