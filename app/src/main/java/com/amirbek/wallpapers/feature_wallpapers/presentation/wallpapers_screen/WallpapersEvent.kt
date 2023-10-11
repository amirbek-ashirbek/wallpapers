package com.amirbek.wallpapers.feature_wallpapers.presentation.wallpapers_screen

import com.amirbek.wallpapers.feature_wallpapers.domain.model.Wallpaper
import com.amirbek.wallpapers.feature_wallpapers.domain.model.WallpaperCategory

sealed class WallpapersEvent {
	data class CategoryItemClicked(val category: WallpaperCategory) : WallpapersEvent()
	data class FavouriteIconClicked(val wallpaper: Wallpaper) : WallpapersEvent()
}