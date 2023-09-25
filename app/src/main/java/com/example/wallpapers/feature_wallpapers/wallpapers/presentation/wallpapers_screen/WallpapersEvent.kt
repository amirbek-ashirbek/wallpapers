package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen

import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.Wallpaper

sealed class WallpapersEvent {
	data class WallpaperClicked(val wallpaper: Wallpaper) : WallpapersEvent()
	data class DownloadClicked(val wallpaper: Wallpaper) : WallpapersEvent()
	data class ApplyClicked(val wallpaper: Wallpaper) : WallpapersEvent()
	data object WallpaperDismissed : WallpapersEvent()
	data object ApplyDialogDismissed: WallpapersEvent()
}