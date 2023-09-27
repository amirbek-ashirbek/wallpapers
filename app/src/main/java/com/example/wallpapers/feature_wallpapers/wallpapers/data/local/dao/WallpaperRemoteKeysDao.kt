package com.example.wallpapers.feature_wallpapers.wallpapers.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperRemoteKeys

@Dao
interface WallpaperRemoteKeysDao {

	@Query("SELECT * FROM wallpapers_remote_keys WHERE id =:id AND categoryId =:categoryId")
	suspend fun getRemoteKeys(id: String, categoryId: String): WallpaperRemoteKeys

	@Upsert
	suspend fun addAllRemoteKeys(remoteKeys: List<WallpaperRemoteKeys>)

	@Query("DELETE FROM wallpapers_remote_keys WHERE categoryId =:categoryId")
	suspend fun clearAll(categoryId: String)

}