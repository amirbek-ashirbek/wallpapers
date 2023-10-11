package com.amirbek.wallpapers.feature_settings.domain.use_case

import com.amirbek.wallpapers.feature_settings.domain.SettingsDataStore
import javax.inject.Inject

class SaveDarkThemeUseCase @Inject constructor(
	private val settingsDataStore: SettingsDataStore
) {

	suspend fun execute(isDarkTheme: Boolean) {
		settingsDataStore.saveDarkTheme(isDarkTheme = isDarkTheme)
	}

}