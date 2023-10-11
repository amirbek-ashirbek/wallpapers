package com.amirbek.wallpapers.feature_wallpapers.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amirbek.wallpapers.util.Constants.WALLPAPERS_REMOTE_KEYS_TABLE

@Entity(tableName = WALLPAPERS_REMOTE_KEYS_TABLE)
data class WallpaperRemoteKeys(
	@PrimaryKey(autoGenerate = false)
	val id: String,
	val categoryId: String,
	val prevPage: Int?,
	val nextPage: Int?
)
