package com.example.wallpapers.di

import android.app.DownloadManager
import android.content.Context
import android.net.ConnectivityManager
import coil.ImageLoader
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.wallpapers.feature_wallpapers.data.DownloaderImpl
import com.example.wallpapers.feature_wallpapers.data.remote.api.UnsplashApi
import com.example.wallpapers.feature_wallpapers.domain.Downloader
import com.example.wallpapers.util.Constants.UNSPLASH_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	@Singleton
	fun provideUnsplashApi(retrofit: Retrofit): UnsplashApi {
		return retrofit.create(UnsplashApi::class.java)
	}

	@Provides
	@Singleton
	fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
		return Retrofit.Builder()
			.client(okHttpClient)
			.baseUrl(UNSPLASH_BASE_URL)
			.addConverterFactory(MoshiConverterFactory.create())
			.build()
	}

	@Provides
	@Singleton
	fun provideHttpClient(@ApplicationContext context: Context): OkHttpClient {
		return OkHttpClient.Builder()
			.addInterceptor(ChuckerInterceptor(context = context))
			.build()
	}

	@Provides
	@Singleton
	fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
		return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
	}

	@Singleton
	@Provides
	fun provideImageLoader(@ApplicationContext context: Context): ImageLoader {
		return ImageLoader(context = context)
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

}