package com.example.wallpapers.feature_wallpapers.wallpapers.data

import android.app.DownloadManager
import android.os.Environment
import androidx.core.net.toUri
import com.example.wallpapers.BuildConfig
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.Downloader
import javax.inject.Inject

class DownloaderImpl @Inject constructor(
	private val downloadManager: DownloadManager
) : Downloader {

	override fun downloadFile(url: String, id: String): Long {

		if (isAnyFileDownloading()) {
			return -1L
		}

		val request = DownloadManager.Request(url.toUri())
			.addRequestHeader("Authorization", "Client-ID ${BuildConfig.UNSPLASH_API_KEY}")
			.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
			.setTitle("$id.jpg")
			.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg")

		return downloadManager.enqueue(request)
	}

	private fun isAnyFileDownloading(): Boolean {
		val query = DownloadManager.Query().apply {
			setFilterByStatus(
				DownloadManager.STATUS_PAUSED or
				DownloadManager.STATUS_PENDING or
				DownloadManager.STATUS_RUNNING or
				DownloadManager.STATUS_FAILED
			)
		}
		val cursor = downloadManager.query(query)

		val isDownloading = cursor.count > 0
		cursor.close()

		return isDownloading
	}

}