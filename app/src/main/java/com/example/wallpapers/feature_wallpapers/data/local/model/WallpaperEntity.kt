package com.example.wallpapers.feature_wallpapers.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wallpapers.feature_wallpapers.domain.model.Wallpaper
import com.example.wallpapers.util.Constants.WALLPAPERS_TABLE

@Entity(tableName = WALLPAPERS_TABLE)
data class WallpaperEntity(
	@PrimaryKey
	val id: String,
	val url: String
) {
	companion object {
		fun toWallpaper(wallpaperEntity: WallpaperEntity): Wallpaper {
			return Wallpaper(
				id = wallpaperEntity.id,
				url = wallpaperEntity.url
			)
		}

		fun toWallpaperEntity(wallpaper: Wallpaper): WallpaperEntity {
			return WallpaperEntity(
				id = wallpaper.id,
				url = wallpaper.url
			)
		}
	}
}
