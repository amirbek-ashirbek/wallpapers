package com.amirbek.wallpapers.feature_wallpapers.domain.use_case

import androidx.paging.PagingData
import com.amirbek.wallpapers.feature_wallpapers.domain.repository.WallpaperRepository
import com.amirbek.wallpapers.feature_wallpapers.domain.model.WallpaperCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesPagedUseCase @Inject constructor(
	private val wallpaperRepository: WallpaperRepository
) {

	fun execute(): Flow<PagingData<WallpaperCategory>> {
		return wallpaperRepository.getAllWallpaperCategoriesPaged()
	}

}