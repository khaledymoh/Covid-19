package io.covid19.update

import android.Manifest
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import io.covid19.core.activities.BaseActivity
import io.covid19.core.utils.Activities.Update.RELEASE_CONFIG_EXTRA
import io.covid19.core.utils.show
import io.covid19.core.utils.softHide
import io.covid19.data.models.ReleaseConfig
import io.covid19.update.databinding.ActivityUpdateBinding
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import javax.inject.Inject

@RuntimePermissions
class UpdateActivity : BaseActivity(), UpdateDownloadCallback {

    @Inject
    lateinit var updateDownloadManager: UpdateDownloadManager

    private var releaseConfig: ReleaseConfig? = null
    private var binding: ActivityUpdateBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()
        initIntentExtra()
        handelWindowViewDimensions()
        bindView()
        initViewListeners()
    }

    private fun initContentView() {
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
    }

    private fun bindView() {
        binding?.releaseConfig = releaseConfig
        binding?.executePendingBindings()
    }

    private fun initViewListeners() {
        binding?.buttonUpdate?.setOnClickListener {
            releaseConfig?.let { updateDownloadManager.enqueueDownload(it) }
        }

        binding?.buttonIgnore?.setOnClickListener { finish() }
    }

    private fun handelWindowViewDimensions() {
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        binding?.cardViewUpdateContainer?.layoutParams =
            ConstraintLayout.LayoutParams(
                metrics.widthPixels - WINDOW_VIEW_HORIZONTAL_MARGIN,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
    }

    private fun initIntentExtra() {
        if (intent.hasExtra(RELEASE_CONFIG_EXTRA)) {
            releaseConfig = intent.getParcelableExtra(RELEASE_CONFIG_EXTRA) as ReleaseConfig
        }
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun startDownload() {
        releaseConfig?.let {
            updateDownloadManager.enqueueDownload(it)
        }
    }

    override fun startDownloading() {
        binding?.progressBar.show()
        binding?.textViewUpdateDownloading.show()
        binding?.buttonUpdate.softHide()
        binding?.buttonIgnore.softHide()
    }

    override fun finishDownloading() {
        finish()
    }

    private companion object {

        private const val WINDOW_VIEW_HORIZONTAL_MARGIN = 100
    }
}
