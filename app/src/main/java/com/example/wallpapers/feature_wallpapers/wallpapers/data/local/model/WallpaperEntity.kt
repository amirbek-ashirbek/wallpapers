package com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.Wallpaper
import com.example.wallpapers.util.Constants.WALLPAPERS_TABLE

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