package com.amirbek.wallpapers.feature_wallpapers.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amirbek.wallpapers.feature_wallpapers.domain.model.Wallpaper
import com.amirbek.wallpapers.util.Constants.WALLPAPERS_TABLE

@Entity(tableName = WALLPAPERS_TABLE)
data class WallpaperEntity(
	@PrimaryKey
	val id: String,
	val categoryId: String,
	val url: String,
	val downloadUrl: String,
	val isFavourite: Boolean
) {
	companion object {
		fun toWallpaper(wallpaperEntity: WallpaperEntity): Wallpaper {
			return Wallpaper(
				id = wallpaperEntity.id,
				categoryId = wallpaperEntity.categoryId,
				url = wallpaperEntity.url,
				downloadUrl = wallpaperEntity.downloadUrl,
				isFavourite = wallpaperEntity.isFavourite
			)
		}

		fun toWallpaperEntity(wallpaper: Wallpaper): WallpaperEntity {
			return WallpaperEntity(
				id = wallpaper.id,
				categoryId = wallpaper.categoryId,
				url = wallpaper.url,
				downloadUrl = wallpaper.downloadUrl,
				isFavourite = wallpaper.isFavourite
			)
		}
	}
}