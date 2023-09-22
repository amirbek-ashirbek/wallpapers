package com.example.wallpapers.feature_wallpapers.wallpapers.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperEntity

@Dao
interface WallpaperDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(wallpapers: List<WallpaperEntity>)

	@Query("SELECT * FROM wallpapers WHERE categoryId LIKE :categoryId")
	fun pagingSource(categoryId: String): PagingSource<Int, WallpaperEntity>

	@Query("DELETE FROM  wallpapers WHERE categoryId LIKE :categoryId")
	suspend fun clearAll(categoryId: String)

}