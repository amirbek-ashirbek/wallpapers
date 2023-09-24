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

	override fun downloadFile(url: String): Long {
		val request = DownloadManager.Request(url.toUri())
			.addRequestHeader("Authorization", "Client-ID ${BuildConfig.UNSPLASH_API_KEY}")
			.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
			.setTitle("wallpaper.jpg")
			.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg")

		return downloadManager.enqueue(request)
	}


}