package com.example.wallpapers.feature_wallpapers.wallpapers.domain.use_case

import com.example.wallpapers.feature_wallpapers.wallpapers.domain.Downloader
import javax.inject.Inject

class DownloadWallpaperUseCase @Inject constructor(
	private val downloader: Downloader
) {

	fun execute(url: String, wallpaperId: String): Long {
		return downloader.downloadFile(url = url, id = wallpaperId)
	}

}