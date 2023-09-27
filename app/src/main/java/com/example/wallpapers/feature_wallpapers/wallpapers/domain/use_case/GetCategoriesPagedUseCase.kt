package com.example.wallpapers.feature_wallpapers.wallpapers.domain.use_case

import androidx.paging.PagingData
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.WallpaperRepository
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.WallpaperCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesPagedUseCase @Inject constructor(
	private val wallpaperRepository: WallpaperRepository
) {

	fun execute(): Flow<PagingData<WallpaperCategory>> {
		return wallpaperRepository.getAllWallpaperCategoriesPaged()
	}

}