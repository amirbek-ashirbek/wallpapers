package com.example.wallpapers.feature_wallpapers.presentation.wallpapers_screen

import com.example.wallpapers.feature_wallpapers.domain.model.WallpaperCategory

sealed class WallpapersEvent {
	data class CategoryItemClicked(val category: WallpaperCategory) : WallpapersEvent()
}