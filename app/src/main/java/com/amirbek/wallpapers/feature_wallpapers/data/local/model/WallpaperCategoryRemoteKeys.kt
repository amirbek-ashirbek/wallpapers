package com.amirbek.wallpapers.feature_wallpapers.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amirbek.wallpapers.util.Constants.WALLPAPER_CATEGORIES_REMOTE_KEYS_TABLE

@Entity(tableName = WALLPAPER_CATEGORIES_REMOTE_KEYS_TABLE)
data class WallpaperCategoryRemoteKeys(
	@PrimaryKey(autoGenerate = false)
	val id: String,
	val prevPage: Int?,
	val nextPage: Int?
)
