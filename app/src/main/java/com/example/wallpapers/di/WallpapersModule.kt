package com.example.wallpapers.di

import android.app.WallpaperManager
import android.content.Context
import coil.ImageLoader
import com.example.wallpapers.feature_wallpapers.data.local.database.WallpaperDatabase
import com.example.wallpapers.feature_wallpapers.data.remote.api.UnsplashApi
import com.example.wallpapers.feature_wallpapers.data.repository.WallpaperRepositoryImpl
import com.example.wallpapers.feature_wallpapers.domain.repository.WallpaperRepository
import com.example.wallpapers.feature_wallpapers.presentation.WallpaperSetter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
		return WallpaperRepositoryImpl(
			unsplashApi = unsplashApi,
			wallpaperDatabase = wallpaperDatabase,
		)
	}

	@Singleton
	@Provides
	fun provideWallpaperManager(@ApplicationContext context: Context): WallpaperManager {
		return context.getSystemService(WallpaperManager::class.java)
	}

	@Singleton
	@Provides
	fun provideWallpaperSetter(
		@ApplicationContext context: Context,
		imageLoader: ImageLoader,
		wallpaperManager: WallpaperManager
	): WallpaperSetter {
		return WallpaperSetter(
			context = context,
			imageLoader = imageLoader,
			wallpaperManager = wallpaperManager
		)
	}

}