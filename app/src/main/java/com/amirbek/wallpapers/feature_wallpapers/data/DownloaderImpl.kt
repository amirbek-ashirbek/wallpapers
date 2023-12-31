package com.amirbek.wallpapers.feature_wallpapers.data

import android.app.DownloadManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Environment
import androidx.core.net.toUri
import com.amirbek.wallpapers.BuildConfig
import com.amirbek.wallpapers.feature_wallpapers.domain.Downloader
import com.amirbek.wallpapers.util.DownloadResult
import javax.inject.Inject

class DownloaderImpl @Inject constructor(
	private val downloadManager: DownloadManager,
	private val connectivityManager: ConnectivityManager
) : Downloader {

	override fun downloadFile(url: String, id: String): DownloadResult {

		try {
			if (isAnyFileDownloading()) {
				return DownloadResult.OtherError(message = null)
			}

			if (!isInternetConnected()) {
				return DownloadResult.InternetError
			}

			val request = DownloadManager.Request(url.toUri())
				.addRequestHeader("Authorization", "Client-ID ${BuildConfig.UNSPLASH_API_KEY}")
				.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
				.setTitle("$id.jpg")
				.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg")

			val downloadId = downloadManager.enqueue(request)
			return DownloadResult.Success(downloadId)
		} catch(e: Exception) {
			return DownloadResult.OtherError(e.message)
		}

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

	private fun isInternetConnected(): Boolean {

		val network = connectivityManager.activeNetwork
		val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

		return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
	}

}