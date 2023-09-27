package com.example.wallpapers.di

import android.content.Context
import com.example.wallpapers.feature_settings.data.SettingsDataStoreImpl
import com.example.wallpapers.feature_settings.domain.SettingsDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

	@Singleton
	@Provides
	fun provideSettingsDataStore(@ApplicationContext context: Context): SettingsDataStore {
		return SettingsDataStoreImpl(context = context)
	}

}