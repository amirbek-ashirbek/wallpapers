package com.example.wallpapers.feature_wallpapers.wallpapers.domain.use_case

import androidx.paging.PagingData
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.WallpaperRepository
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.Wallpaper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteWallpapersUseCase @Inject constructor(
	private val wallpaperRepository: WallpaperRepository
) {
	fun execute(): Flow<PagingData<Wallpaper>> {
		return wallpaperRepository.getFavouriteWallpapers()
	}

}