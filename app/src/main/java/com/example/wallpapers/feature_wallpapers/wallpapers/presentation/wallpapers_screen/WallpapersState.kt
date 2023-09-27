package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen

import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.WallpaperCategory

data class WallpapersState(
	val categories: List<WallpaperCategory>? = null
)