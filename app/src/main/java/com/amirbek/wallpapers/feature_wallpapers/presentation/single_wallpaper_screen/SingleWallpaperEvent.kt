package com.amirbek.wallpapers.feature_wallpapers.presentation.single_wallpaper_screen

import com.amirbek.wallpapers.feature_wallpapers.presentation.WallpaperApplyScreen

sealed class SingleWallpaperEvent {
	data object DownloadClicked : SingleWallpaperEvent()
	data object ApplyButtonClicked : SingleWallpaperEvent()
	data class WallpaperApplied(val screen: WallpaperApplyScreen) : SingleWallpaperEvent()
	data object FavouriteClicked : SingleWallpaperEvent()
	data object ApplyDialogDismissed: SingleWallpaperEvent()
	data object InternetErrorDialogDismissed: SingleWallpaperEvent()
}