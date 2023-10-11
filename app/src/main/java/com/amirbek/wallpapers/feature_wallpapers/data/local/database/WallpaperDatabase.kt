package com.amirbek.wallpapers.feature_wallpapers.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amirbek.wallpapers.feature_wallpapers.data.local.dao.WallpaperCategoryDao
import com.amirbek.wallpapers.feature_wallpapers.data.local.dao.WallpaperCategoryRemoteKeysDao
import com.amirbek.wallpapers.feature_wallpapers.data.local.dao.WallpaperDao
import com.amirbek.wallpapers.feature_wallpapers.data.local.dao.WallpaperRemoteKeysDao
import com.amirbek.wallpapers.feature_wallpapers.data.local.model.WallpaperCategoryEntity
import com.amirbek.wallpapers.feature_wallpapers.data.local.model.WallpaperCategoryRemoteKeys
import com.amirbek.wallpapers.feature_wallpapers.data.local.model.WallpaperEntity
import com.amirbek.wallpapers.feature_wallpapers.data.local.model.WallpaperRemoteKeys

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