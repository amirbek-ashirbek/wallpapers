package com.amirbek.wallpapers.feature_wallpapers.domain.use_case

import com.amirbek.wallpapers.feature_wallpapers.domain.repository.WallpaperRepository
import com.amirbek.wallpapers.feature_wallpapers.domain.model.Wallpaper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSingleWallpaperUseCase @Inject constructor(
	private val wallpaperRepository: WallpaperRepository
) {

	fun execute(wallpaperId: String): Flow<Wallpaper> {
		return wallpaperRepository.getWallpaperById(wallpaperId = wallpaperId)
	}

}