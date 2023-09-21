package com.example.wallpapers.feature_wallpapers.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.wallpapers.feature_wallpapers.data.WallpaperRemoteMediator
import com.example.wallpapers.feature_wallpapers.data.local.WallpaperDatabase
import com.example.wallpapers.feature_wallpapers.data.local.model.WallpaperEntity.Companion.toWallpaper
import com.example.wallpapers.feature_wallpapers.data.remote.UnsplashApi
import com.example.wallpapers.feature_wallpapers.domain.model.Wallpaper
import com.example.wallpapers.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class WallpaperRepository @Inject constructor(
	private val unsplashApi: UnsplashApi,
	private val wallpaperDatabase: WallpaperDatabase
) {

	fun getAllWallpapers(): Flow<PagingData<Wallpaper>> {
		val pagingSourceFactory = { wallpaperDatabase.wallpaperDao().pagingSource() }
		return Pager(
			config = PagingConfig(pageSize = ITEMS_PER_PAGE),
			remoteMediator = WallpaperRemoteMediator(
				unsplashApi = unsplashApi,
				wallpaperDatabase = wallpaperDatabase
			),
			pagingSourceFactory = pagingSourceFactory
		).flow
			.map { pagingData ->
				pagingData.map { wallpaperEntity ->
					toWallpaper(wallpaperEntity = wallpaperEntity)
				}
		}
	}

}