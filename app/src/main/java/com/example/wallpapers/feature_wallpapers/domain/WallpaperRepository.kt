package com.example.wallpapers.feature_wallpapers.domain

import androidx.paging.PagingData
import com.example.wallpapers.feature_wallpapers.domain.model.Wallpaper
import com.example.wallpapers.feature_wallpapers.domain.model.WallpaperCategory
import kotlinx.coroutines.flow.Flow

interface WallpaperRepository {

	fun getAllWallpaperCategoriesPaged(): Flow<PagingData<WallpaperCategory>>

	fun getAllWallpaperCategories(): Flow<List<WallpaperCategory>>

	fun getWallpapersByCategory(categoryId: String): Flow<PagingData<Wallpaper>>

	fun getWallpaperById(wallpaperId: String): Flow<Wallpaper>

	suspend fun updateWallpaper(wallpaper: Wallpaper)

	fun getFavouriteWallpapers(): Flow<PagingData<Wallpaper>>

}