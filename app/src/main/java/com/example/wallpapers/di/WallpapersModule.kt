package com.example.wallpapers.di

import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.WallpaperDatabase
import com.example.wallpapers.feature_wallpapers.wallpapers.data.remote.UnsplashApi
import com.example.wallpapers.feature_wallpapers.wallpapers.data.repository.WallpaperRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WallpapersModule {

	@Singleton
	@Provides
	fun provideWallpaperRepository(
		unsplashApi: UnsplashApi,
		wallpaperDatabase: WallpaperDatabase,
	): WallpaperRepository {
		return WallpaperRepository(
			unsplashApi = unsplashApi,
			wallpaperDatabase = wallpaperDatabase,
		)
	}

}