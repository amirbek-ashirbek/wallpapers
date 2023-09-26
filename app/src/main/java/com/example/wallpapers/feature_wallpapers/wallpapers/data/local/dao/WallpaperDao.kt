package com.example.wallpapers.feature_wallpapers.wallpapers.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperEntity

@Dao
interface WallpaperDao {

	@Upsert
	suspend fun upsertAll(wallpapers: List<WallpaperEntity>)

	@Query("SELECT * FROM wallpapers WHERE categoryId =:categoryId")
	fun pagingSource(categoryId: String): PagingSource<Int, WallpaperEntity>

	@Query("DELETE FROM  wallpapers WHERE categoryId =:categoryId")
	suspend fun clearAll(categoryId: String)

	@Query("SELECT * FROM wallpapers WHERE isFavourite = 1")
	suspend fun getFavouriteWallpapers(): PagingSource<Int, WallpaperEntity>

	@Update
	suspend fun updateWallpaper(wallpaperEntity: WallpaperEntity)

}