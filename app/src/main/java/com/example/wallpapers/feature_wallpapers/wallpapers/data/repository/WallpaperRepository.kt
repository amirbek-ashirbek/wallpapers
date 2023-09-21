package com.example.wallpapers.feature_wallpapers.wallpapers.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperCategoryEntity
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperCategoryEntity.Companion.toWallpaperCategory
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperEntity
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperEntity.Companion.toWallpaper
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.Wallpaper
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.WallpaperCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WallpaperRepository @Inject constructor(
	private val wallpapersPager: Pager<Int, WallpaperEntity>,
	private val wallpaperCategoriesPager: Pager<Int, WallpaperCategoryEntity>
) {

	fun getAllWallpapers(): Flow<PagingData<Wallpaper>> {
		return wallpapersPager.flow
			.map { pagingData ->
				pagingData.map { wallpaperEntity ->
					toWallpaper(wallpaperEntity = wallpaperEntity)
				}
		}
	}

	fun getAllWallpaperCategories(): Flow<PagingData<WallpaperCategory>> {
		return wallpaperCategoriesPager.flow.map { pagingData ->
			pagingData.map { categoryEntity ->
				toWallpaperCategory(entity = categoryEntity)
			}
		}
	}

}