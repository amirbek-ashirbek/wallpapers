package com.example.wallpapers.feature_wallpapers.presentation.wallpapers_screen

import com.example.wallpapers.feature_wallpapers.domain.model.WallpaperCategory

data class WallpapersState(
	val categories: List<WallpaperCategory>? = null,
	val selectedCategoryId: String
)