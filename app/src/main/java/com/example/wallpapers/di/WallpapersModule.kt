package com.example.wallpapers.di

import android.app.DownloadManager
import android.content.Context
import com.example.wallpapers.feature_wallpapers.wallpapers.data.DownloaderImpl
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.WallpaperDatabase
import com.example.wallpapers.feature_wallpapers.wallpapers.data.remote.UnsplashApi
import com.example.wallpapers.feature_wallpapers.wallpapers.data.repository.WallpaperRepository
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.Downloader
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
		return WallpaperRepository(
			unsplashApi = unsplashApi,
			wallpaperDatabase = wallpaperDatabase,
		)
	}

	@Singleton
	@Provides
	fun provideDownloader(downloadManager: DownloadManager): Downloader {
		return DownloaderImpl(downloadManager = downloadManager)
	}

	@Singleton
	@Provides
	fun provideDownloadManager(@ApplicationContext context: Context): DownloadManager {
		return context.getSystemService(DownloadManager::class.java)
	}

}