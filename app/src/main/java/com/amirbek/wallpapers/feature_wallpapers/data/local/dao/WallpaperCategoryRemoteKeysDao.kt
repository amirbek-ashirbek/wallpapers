package com.amirbek.wallpapers.feature_wallpapers.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.amirbek.wallpapers.feature_wallpapers.data.local.model.WallpaperCategoryRemoteKeys

@Dao
interface WallpaperCategoryRemoteKeysDao {

	@Query("SELECT * FROM wallpaper_categories_remote_keys WHERE id =:id")
	suspend fun getRemoteKeys(id: String): WallpaperCategoryRemoteKeys

	@Upsert
	suspend fun addAllRemoteKeys(remoteKeys: List<WallpaperCategoryRemoteKeys>)

	@Query("DELETE FROM wallpaper_categories_remote_keys")
	suspend fun clearAll()

}