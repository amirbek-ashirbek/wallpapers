package com.example.wallpapers.feature_wallpapers.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.wallpapers.feature_wallpapers.data.local.model.WallpaperEntity

@Dao
interface WallpaperDao {

	@Upsert
	suspend fun upsertAll(wallpapers: List<WallpaperEntity>)

	@Query("SELECT * FROM wallpapers")
	fun pagingSource(): PagingSource<Int, WallpaperEntity>

	@Query("DELETE FROM  wallpapers")
	suspend fun clearAll()

}