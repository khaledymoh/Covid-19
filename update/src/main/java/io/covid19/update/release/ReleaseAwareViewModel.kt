package io.covid19.update.release

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.covid19.core.BaseViewModel
import io.covid19.data.BuildConfig
import io.covid19.data.models.ReleaseConfig
import io.covid19.data.network.Result
import io.covid19.data.repositories.release.ReleaseRepository
import kotlinx.coroutines.launch

class ReleaseAwareViewModel(
    private var releaseRepository: ReleaseRepository
) : BaseViewModel() {

    private var releaseConfig: ReleaseConfig? = null
    private var _updateAvailableLiveData = MutableLiveData(false)

    fun checkNewRelease() {
        viewModelScope.launch {
            val result = tryResult { releaseRepository.getCurrentReleaseConfig() }
            if (result.isSuccess()) {
                releaseConfig = (result as Result.Success<ReleaseConfig>).data
                checkIfNewUpdate(releaseConfig)
            }
        }
    }

    fun getUpdateAvailableLiveData(): LiveData<Boolean> {
        return _updateAvailableLiveData
    }

    fun getReleaseConfig(): ReleaseConfig? {
        return releaseConfig
    }

    private fun checkIfNewUpdate(releaseConfig: ReleaseConfig?) {
        if ((releaseConfig?.versionCode ?: 0) > BuildConfig.VERSION_CODE) {
            _updateAvailableLiveData.value = true
        }
    }
}