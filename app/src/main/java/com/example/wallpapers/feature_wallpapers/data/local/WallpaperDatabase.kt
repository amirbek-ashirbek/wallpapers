package com.example.wallpapers.feature_wallpapers.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wallpapers.feature_wallpapers.data.local.dao.WallpaperDao
import com.example.wallpapers.feature_wallpapers.data.local.dao.WallpaperRemoteKeysDao
import com.example.wallpapers.feature_wallpapers.data.local.model.WallpaperEntity
import com.example.wallpapers.feature_wallpapers.data.local.model.WallpaperRemoteKeys

@Database(
	entities = [WallpaperEntity::class, WallpaperRemoteKeys::class],
	version = 1
)
abstract class WallpaperDatabase : RoomDatabase() {

	abstract fun wallpaperDao(): WallpaperDao
	abstract fun wallpaperRemoteKeysDao(): WallpaperRemoteKeysDao

}