package com.amirbek.wallpapers.feature_wallpapers.domain

import com.amirbek.wallpapers.util.DownloadResult

interface Downloader {

	fun downloadFile(url: String, id: String): DownloadResult

}