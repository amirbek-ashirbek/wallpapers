package com.example.wallpapers.feature_wallpapers.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.wallpapers.feature_wallpapers.data.local.model.WallpaperEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WallpaperDao {

	@Upsert
	suspend fun upsertAll(wallpapers: List<WallpaperEntity>)

	@Query("SELECT * FROM wallpapers WHERE categoryId =:categoryId")
	fun pagingSource(categoryId: String): PagingSource<Int, WallpaperEntity>

	@Query("DELETE FROM  wallpapers WHERE categoryId =:categoryId AND isFavourite = 0")
	suspend fun clearAll(categoryId: String)

	@Query("SELECT * FROM wallpapers WHERE isFavourite = 1")
	fun getFavouriteWallpapers(): PagingSource<Int, WallpaperEntity>

	@Update
	suspend fun updateWallpaper(wallpaperEntity: WallpaperEntity)

	@Query("SELECT * FROM wallpapers WHERE id =:id")
	fun getWallpaperById(id: String): Flow<WallpaperEntity>

}