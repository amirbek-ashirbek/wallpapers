package com.amirbek.wallpapers.feature_wallpapers.domain.use_case

import com.amirbek.wallpapers.feature_wallpapers.domain.Downloader
import com.amirbek.wallpapers.util.DownloadResult
import javax.inject.Inject

class DownloadWallpaperUseCase @Inject constructor(
	private val downloader: Downloader
) {

	fun execute(url: String, wallpaperId: String): DownloadResult {
		return downloader.downloadFile(url = url, id = wallpaperId)
	}

}