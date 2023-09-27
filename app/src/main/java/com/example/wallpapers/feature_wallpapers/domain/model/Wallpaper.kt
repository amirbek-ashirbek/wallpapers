package com.example.wallpapers.feature_wallpapers.domain.model

data class Wallpaper(
	val id: String,
	val categoryId: String,
	val url: String,
	val downloadUrl: String,
	val isFavourite: Boolean
)