package com.example.wallpapers.feature_wallpapers.wallpapers.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperRemoteKeys

@Dao
interface WallpaperRemoteKeysDao {

	@Query("SELECT * FROM wallpapers_remote_keys WHERE id =:id")
	suspend fun getRemoteKeys(id: String): WallpaperRemoteKeys

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addAllRemoteKeys(remoteKeys: List<WallpaperRemoteKeys>)

	@Query("DELETE FROM wallpapers_remote_keys")
	suspend fun clearAll()

}