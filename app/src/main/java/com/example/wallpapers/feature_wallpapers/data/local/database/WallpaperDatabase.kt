package com.example.wallpapers.feature_wallpapers.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wallpapers.feature_wallpapers.data.local.dao.WallpaperCategoryDao
import com.example.wallpapers.feature_wallpapers.data.local.dao.WallpaperCategoryRemoteKeysDao
import com.example.wallpapers.feature_wallpapers.data.local.dao.WallpaperDao
import com.example.wallpapers.feature_wallpapers.data.local.dao.WallpaperRemoteKeysDao
import com.example.wallpapers.feature_wallpapers.data.local.model.WallpaperCategoryEntity
import com.example.wallpapers.feature_wallpapers.data.local.model.WallpaperCategoryRemoteKeys
import com.example.wallpapers.feature_wallpapers.data.local.model.WallpaperEntity
import com.example.wallpapers.feature_wallpapers.data.local.model.WallpaperRemoteKeys

@Database(
	entities = [
		WallpaperEntity::class,
		WallpaperRemoteKeys::class,
		WallpaperCategoryEntity::class,
		WallpaperCategoryRemoteKeys::class
],
	version = 1
)
abstract class WallpaperDatabase : RoomDatabase() {

	abstract fun wallpaperDao(): WallpaperDao
	abstract fun wallpaperRemoteKeysDao(): WallpaperRemoteKeysDao
	abstract fun wallpaperCategoryDao(): WallpaperCategoryDao
	abstract fun wallpaperCategoryRemoteKeysDao(): WallpaperCategoryRemoteKeysDao

}