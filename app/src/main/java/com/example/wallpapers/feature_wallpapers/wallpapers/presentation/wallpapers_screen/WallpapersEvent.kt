package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen

import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.Wallpaper
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.WallpaperApplyScreen

sealed class WallpapersEvent {
	data class WallpaperClicked(val wallpaper: Wallpaper) : WallpapersEvent()
	data class DownloadClicked(val wallpaper: Wallpaper) : WallpapersEvent()
	data class ApplyButtonClicked(val wallpaper: Wallpaper) : WallpapersEvent()
	data class WallpaperApplied(val screen: WallpaperApplyScreen) : WallpapersEvent()
	data class FavouriteClicked(val wallpaper: Wallpaper) : WallpapersEvent()
	data object WallpaperDismissed : WallpapersEvent()
	data object ApplyDialogDismissed: WallpapersEvent()
	data object InternetErrorDialogDismissed: WallpapersEvent()
}