package com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.WallpaperCategory
import com.example.wallpapers.util.Constants.WALLPAPER_CATEGORIES_TABLE

@Entity(tableName = WALLPAPER_CATEGORIES_TABLE)
data class WallpaperCategoryEntity(
	@PrimaryKey
	val id: String,
	val title: String,
	val coverPhotoUrl: String
) {

	companion object {
		fun toWallpaperCategory(entity: WallpaperCategoryEntity): WallpaperCategory {
			return WallpaperCategory(
				id = entity.id,
				title = entity.title,
				coverPhotoUrl = entity.coverPhotoUrl
			)
		}
	}

}