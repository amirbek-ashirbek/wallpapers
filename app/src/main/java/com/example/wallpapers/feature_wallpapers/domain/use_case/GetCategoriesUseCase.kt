package com.example.wallpapers.feature_wallpapers.domain.use_case

import com.example.wallpapers.feature_wallpapers.domain.WallpaperRepository
import com.example.wallpapers.feature_wallpapers.domain.model.WallpaperCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
	private val wallpaperRepository: WallpaperRepository
) {

	fun execute(): Flow<List<WallpaperCategory>> {
		return wallpaperRepository.getAllWallpaperCategories()
	}

}