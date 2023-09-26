package com.example.wallpapers.util

sealed class DownloadResult {
	data class Success(val downloadId: Long) : DownloadResult()
	data object InternetError : DownloadResult()
	data class OtherError(val message: String?) : DownloadResult()
}