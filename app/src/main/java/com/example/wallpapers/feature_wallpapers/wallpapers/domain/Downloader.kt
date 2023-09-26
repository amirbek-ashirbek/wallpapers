package com.example.wallpapers.feature_wallpapers.wallpapers.domain

import com.example.wallpapers.util.DownloadResult

interface Downloader {

	fun downloadFile(url: String, id: String): DownloadResult

}