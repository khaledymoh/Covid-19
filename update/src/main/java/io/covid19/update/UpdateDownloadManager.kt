package io.covid19.update

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.content.FileProvider
import io.covid19.data.BuildConfig
import io.covid19.data.models.ReleaseConfig
import java.io.File

class UpdateDownloadManager(private val context: Context) {

    private var downloadCallback = context as UpdateDownloadCallback

    fun enqueueDownload(releaseConfig: ReleaseConfig) {
        downloadCallback.startDownloading()

        var destination =
            context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/"
        destination += releaseConfig.apkName

        val uri = Uri.parse("$FILE_BASE_PATH$destination")

        val file = File(destination)
        if (file.exists()) file.delete()

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadUri = Uri.parse(releaseConfig.apkUrl)
        val request = DownloadManager.Request(downloadUri).apply {
            setMimeType(MIME_TYPE)
            setTitle(context.getString(R.string.title_download_manager_downloading_covid_19))
            setDescription(context.getString(R.string.description_download_manager_downloading))
            setDestinationUri(uri)
        }
        showInstallOption(destination, uri)
        downloadManager.enqueue(request)
    }

    private fun showInstallOption(destination: String, uri: Uri) {

        val onComplete = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    val contentUri = FileProvider.getUriForFile(
                        context,
                        BuildConfig.CURRENT_APPLICATION_ID + PROVIDER_PATH,
                        File(destination)
                    )
                    val installIntent = Intent(Intent.ACTION_VIEW).apply {
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true)
                        data = contentUri
                    }
                    downloadCallback.finishDownloading()
                    context.startActivity(installIntent)
                    context.unregisterReceiver(this)
                } else {
                    val install = Intent(Intent.ACTION_VIEW).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        setDataAndType(uri, APP_INSTALL_PATH)
                    }
                    downloadCallback.finishDownloading()
                    context.startActivity(install)
                    context.unregisterReceiver(this)
                }
            }
        }
        context.registerReceiver(onComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    companion object {

        private const val FILE_BASE_PATH = "file://"
        private const val MIME_TYPE = "application/vnd.android.package-archive"
        private const val PROVIDER_PATH = ".provider"
        private const val APP_INSTALL_PATH = "\"application/vnd.android.package-archive\""
    }
}