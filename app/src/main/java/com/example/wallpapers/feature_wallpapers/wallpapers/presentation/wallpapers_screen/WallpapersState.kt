package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen

import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.Wallpaper

data class WallpapersState(
	val isWallpaperVisibleInFullScreen: Boolean = false,
	val wallpaperInFullScreen: Wallpaper? = null,
	val isApplyDialogVisible: Boolean = false
)