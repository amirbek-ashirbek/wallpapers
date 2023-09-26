package com.example.wallpapers.di

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.net.ConnectivityManager
import coil.ImageLoader
import com.example.wallpapers.feature_wallpapers.wallpapers.data.DownloaderImpl
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.WallpaperDatabase
import com.example.wallpapers.feature_wallpapers.wallpapers.data.remote.UnsplashApi
import com.example.wallpapers.feature_wallpapers.wallpapers.data.repository.WallpaperRepositoryImpl
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.Downloader
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.WallpaperRepository
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.WallpaperSetter
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
	fun provideDownloader(downloadManager: DownloadManager, connectivityManager: ConnectivityManager): Downloader {
		return DownloaderImpl(downloadManager = downloadManager, connectivityManager = connectivityManager)
	}

	@Singleton
	@Provides
	fun provideDownloadManager(@ApplicationContext context: Context): DownloadManager {
		return context.getSystemService(DownloadManager::class.java)
	}

	@Singleton
	@Provides
	fun provideWallpaperManager(@ApplicationContext context: Context): WallpaperManager {
		return context.getSystemService(WallpaperManager::class.java)
	}

	@Singleton
	@Provides
	fun provideImageLoader(@ApplicationContext context: Context): ImageLoader {
		return ImageLoader(context = context)
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