package io.covid19.update.release

import android.os.Bundle
import io.covid19.core.activities.BaseActivity
import io.covid19.core.utils.Activities
import io.covid19.core.utils.Activities.Update.RELEASE_CONFIG_EXTRA
import io.covid19.core.utils.intentTo
import io.covid19.core.utils.observe
import javax.inject.Inject

abstract class ReleaseAwareActivity : BaseActivity() {

    @Inject
    internal lateinit var releaseAwareViewModel: ReleaseAwareViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        releaseAwareViewModel.checkNewRelease()
        handelUpdateAvailable()
    }

    private fun handelUpdateAvailable() {
        releaseAwareViewModel.getUpdateAvailableLiveData().observe(this) {
            if (it) {
                startActivity(intentTo(Activities.Update).apply {
                    putExtra(RELEASE_CONFIG_EXTRA, releaseAwareViewModel.getReleaseConfig())
                })
            }
        }
    }
}