package com.example.wallpapers.feature_wallpapers.presentation.single_wallpaper_screen

import com.example.wallpapers.feature_wallpapers.domain.model.Wallpaper

data class SingleWallpaperState(
	val wallpaper: Wallpaper? = null,
	val isApplyDialogVisible: Boolean = false,
	val wallpaperAppliedSuccessfully: Boolean = false,
	val isWallpaperApplying: Boolean = false,
	val isDownloading: Boolean = false,
	val internetError: Boolean = false,
	val otherError: Boolean = false
)