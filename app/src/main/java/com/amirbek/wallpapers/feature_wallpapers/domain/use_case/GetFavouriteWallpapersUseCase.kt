package com.amirbek.wallpapers.feature_wallpapers.domain.use_case

import androidx.paging.PagingData
import com.amirbek.wallpapers.feature_wallpapers.domain.repository.WallpaperRepository
import com.amirbek.wallpapers.feature_wallpapers.domain.model.Wallpaper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteWallpapersUseCase @Inject constructor(
	private val wallpaperRepository: WallpaperRepository
) {
	fun execute(): Flow<PagingData<Wallpaper>> {
		return wallpaperRepository.getFavouriteWallpapers()
	}

}