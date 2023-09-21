package com.example.wallpapers.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.WallpaperDatabase
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperCategoryEntity
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperEntity
import com.example.wallpapers.feature_wallpapers.wallpapers.data.paging.WallpaperCategoryRemoteMediator
import com.example.wallpapers.feature_wallpapers.wallpapers.data.paging.WallpaperRemoteMediator
import com.example.wallpapers.feature_wallpapers.wallpapers.data.remote.UnsplashApi
import com.example.wallpapers.feature_wallpapers.wallpapers.data.repository.WallpaperRepository
import com.example.wallpapers.util.Constants.ITEMS_PER_PAGE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object WallpapersModule {

	@Singleton
	@Provides
	fun provideWallpaperRepository(
		wallpapersPager: Pager<Int, WallpaperEntity>,
		wallpaperCategoriesPager: Pager<Int, WallpaperCategoryEntity>
	): WallpaperRepository {
		return WallpaperRepository(
			wallpapersPager = wallpapersPager,
			wallpaperCategoriesPager = wallpaperCategoriesPager
		)
	}

	@Singleton
	@Provides
	fun provideWallpapersPager(unsplashApi: UnsplashApi, wallpaperDatabase: WallpaperDatabase): Pager<Int, WallpaperEntity > {
		return Pager(
			config = PagingConfig(pageSize = ITEMS_PER_PAGE),
			remoteMediator = WallpaperRemoteMediator(
				unsplashApi = unsplashApi,
				wallpaperDatabase = wallpaperDatabase
			),
			pagingSourceFactory = {
				wallpaperDatabase.wallpaperDao().pagingSource()
			}
		)
	}

	@Singleton
	@Provides
	fun provideWallpaperCategoriesPager(unsplashApi: UnsplashApi, wallpaperDatabase: WallpaperDatabase): Pager<Int, WallpaperCategoryEntity > {
		return Pager(
			config = PagingConfig(pageSize = ITEMS_PER_PAGE),
			remoteMediator = WallpaperCategoryRemoteMediator(
				unsplashApi = unsplashApi,
				wallpaperDatabase = wallpaperDatabase
			),
			pagingSourceFactory = {
				wallpaperDatabase.wallpaperCategoryDao().pagingSource()
			}
		)
	}

}