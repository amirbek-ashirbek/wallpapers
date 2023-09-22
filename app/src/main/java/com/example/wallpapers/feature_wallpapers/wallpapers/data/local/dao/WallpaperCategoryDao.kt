package com.example.wallpapers.feature_wallpapers.wallpapers.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperCategoryEntity

@Dao
interface WallpaperCategoryDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(categories: List<WallpaperCategoryEntity>)

	@Query("SELECT * FROM wallpaper_categories")
	fun pagingSource(): PagingSource<Int, WallpaperCategoryEntity>

	@Query("DELETE FROM  wallpaper_categories")
	suspend fun clearAll()

}