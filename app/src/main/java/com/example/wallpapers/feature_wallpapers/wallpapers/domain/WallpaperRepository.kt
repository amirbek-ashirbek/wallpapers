package com.example.wallpapers.feature_wallpapers.wallpapers.domain

import androidx.paging.PagingData
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.Wallpaper
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.WallpaperCategory
import kotlinx.coroutines.flow.Flow

interface WallpaperRepository {

	fun getAllWallpaperCategories(): Flow<PagingData<WallpaperCategory>>

	fun getWallpapersByCategory(categoryId: String): Flow<PagingData<Wallpaper>>

	suspend fun updateWallpaper(wallpaper: Wallpaper)

}