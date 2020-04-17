package io.covid19.update

interface UpdateDownloadCallback {

    fun startDownloading()

    fun finishDownloading()
}