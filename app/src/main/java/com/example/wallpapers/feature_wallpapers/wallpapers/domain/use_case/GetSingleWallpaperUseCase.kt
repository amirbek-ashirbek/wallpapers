package com.example.wallpapers.feature_wallpapers.wallpapers.domain.use_case

import com.example.wallpapers.feature_wallpapers.wallpapers.domain.WallpaperRepository
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.Wallpaper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSingleWallpaperUseCase @Inject constructor(
	private val wallpaperRepository: WallpaperRepository
) {

	fun execute(wallpaperId: String): Flow<Wallpaper> {
		return wallpaperRepository.getWallpaperById(wallpaperId = wallpaperId)
	}

}