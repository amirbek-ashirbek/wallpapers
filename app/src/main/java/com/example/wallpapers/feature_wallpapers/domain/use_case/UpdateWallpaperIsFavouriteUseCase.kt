package com.example.wallpapers.feature_wallpapers.domain.use_case

import com.example.wallpapers.feature_wallpapers.domain.repository.WallpaperRepository
import com.example.wallpapers.feature_wallpapers.domain.model.Wallpaper
import javax.inject.Inject

class UpdateWallpaperIsFavouriteUseCase @Inject constructor(
	private val wallpaperRepository: WallpaperRepository
) {

	suspend fun execute(wallpaper: Wallpaper) {
		wallpaperRepository.updateWallpaper(wallpaper = wallpaper)
	}

}