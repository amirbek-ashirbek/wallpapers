package com.example.wallpapers.feature_settings.domain.use_case

import com.example.wallpapers.feature_settings.domain.SettingsDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDarkThemeUseCase @Inject constructor(
	private val settingsDataStore: SettingsDataStore
) {

	fun execute(): Flow<Boolean?> {
		return settingsDataStore.isDarkTheme
	}

}