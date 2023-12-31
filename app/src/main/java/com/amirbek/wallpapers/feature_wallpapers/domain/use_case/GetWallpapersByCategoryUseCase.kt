package com.amirbek.wallpapers.feature_wallpapers.domain.use_case

import androidx.paging.PagingData
import com.amirbek.wallpapers.feature_wallpapers.domain.repository.WallpaperRepository
import com.amirbek.wallpapers.feature_wallpapers.domain.model.Wallpaper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWallpapersByCategoryUseCase @Inject constructor(
	private val wallpaperRepository: WallpaperRepository
) {

	fun execute(categoryId: String): Flow<PagingData<Wallpaper>> {
		return wallpaperRepository.getWallpapersByCategory(categoryId = categoryId)
	}

}