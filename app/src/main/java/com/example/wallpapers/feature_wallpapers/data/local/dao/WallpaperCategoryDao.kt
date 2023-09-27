package com.example.wallpapers.feature_wallpapers.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.wallpapers.feature_wallpapers.data.local.model.WallpaperCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WallpaperCategoryDao {

	@Upsert
	suspend fun upsertAll(categories: List<WallpaperCategoryEntity>)

	@Query("SELECT * FROM wallpaper_categories")
	fun pagingSource(): PagingSource<Int, WallpaperCategoryEntity>

	@Query("SELECT * FROM wallpaper_categories")
	fun getAllCategories(): Flow<List<WallpaperCategoryEntity>>

	@Query("DELETE FROM  wallpaper_categories")
	suspend fun clearAll()

}