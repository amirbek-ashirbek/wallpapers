package com.example.wallpapers.feature_wallpapers.wallpapers.domain

interface Downloader {

	fun downloadFile(url: String): Long

}