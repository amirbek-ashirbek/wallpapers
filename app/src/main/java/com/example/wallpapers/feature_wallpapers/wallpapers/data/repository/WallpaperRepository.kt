package com.example.wallpapers.feature_wallpapers.wallpapers.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.WallpaperDatabase
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperCategoryEntity.Companion.toWallpaperCategory
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperEntity.Companion.toWallpaper
import com.example.wallpapers.feature_wallpapers.wallpapers.data.paging.WallpaperCategoryRemoteMediator
import com.example.wallpapers.feature_wallpapers.wallpapers.data.paging.WallpaperRemoteMediator
import com.example.wallpapers.feature_wallpapers.wallpapers.data.remote.UnsplashApi
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.Wallpaper
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.WallpaperCategory
import com.example.wallpapers.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class WallpaperRepository @Inject constructor(
	private val unsplashApi: UnsplashApi,
	private val wallpaperDatabase: WallpaperDatabase
) {

	fun getWallpapersByCategory(categoryId: String): Flow<PagingData<Wallpaper>> {

		val pager = Pager(
			config = PagingConfig(pageSize = Constants.ITEMS_PER_PAGE),
			remoteMediator = WallpaperRemoteMediator(
				unsplashApi = unsplashApi,
				wallpaperDatabase = wallpaperDatabase,
				categoryId = categoryId
			),
			pagingSourceFactory = {
				wallpaperDatabase.wallpaperDao().pagingSource(categoryId = categoryId)
			}
		)
		return pager.flow
			.map { pagingData ->
				pagingData.map { wallpaperEntity ->
					toWallpaper(wallpaperEntity = wallpaperEntity)
				}
		}
	}

	fun getAllWallpaperCategories(): Flow<PagingData<WallpaperCategory>> {

		val pager = Pager(
			config = PagingConfig(pageSize = Constants.ITEMS_PER_PAGE),
			remoteMediator = WallpaperCategoryRemoteMediator(
				unsplashApi = unsplashApi,
				wallpaperDatabase = wallpaperDatabase
			),
			pagingSourceFactory = {
				wallpaperDatabase.wallpaperCategoryDao().pagingSource()
			}
		)

		return pager.flow.map { pagingData ->
			pagingData.map { categoryEntity ->
				toWallpaperCategory(entity = categoryEntity)
			}
		}
	}

}