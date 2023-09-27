package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen

import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.WallpaperCategory

sealed class WallpapersEvent {
	data class CategoryItemClicked(val category: WallpaperCategory) : WallpapersEvent()
}