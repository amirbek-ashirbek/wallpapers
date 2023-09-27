package com.example.wallpapers.feature_wallpapers.presentation.single_wallpaper_screen

data class SingleWallpaperScreenNavArgs(
	val wallpaperId: String,
	val url: String,
	val isFavourite: Boolean
)