package com.example.wallpapers.di

import android.content.Context
import androidx.room.Room
import com.example.wallpapers.feature_wallpapers.data.local.WallpaperDatabase
import com.example.wallpapers.util.Constants.WALLPAPER_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

	@Provides
	@Singleton
	fun provideWallpaperDatabase(@ApplicationContext context: Context): WallpaperDatabase {
		return Room.databaseBuilder(
			context,
			WallpaperDatabase::class.java,
			WALLPAPER_DATABASE
		).build()
	}

}