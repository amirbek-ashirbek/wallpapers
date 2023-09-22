package com.example.wallpapers.feature_wallpapers.wallpapers.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperCategoryRemoteKeys

@Dao
interface WallpaperCategoryRemoteKeysDao {

	@Query("SELECT * FROM wallpaper_categories_remote_keys WHERE id =:id")
	suspend fun getRemoteKeys(id: String): WallpaperCategoryRemoteKeys

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addAllRemoteKeys(remoteKeys: List<WallpaperCategoryRemoteKeys>)

	@Query("DELETE FROM wallpaper_categories_remote_keys")
	suspend fun clearAll()

}