package com.amirbek.wallpapers.feature_wallpapers.presentation.wallpapers_screen

import com.amirbek.wallpapers.feature_wallpapers.domain.model.WallpaperCategory

data class WallpapersState(
	val categories: List<WallpaperCategory>? = null,
	val selectedCategoryId: String
)