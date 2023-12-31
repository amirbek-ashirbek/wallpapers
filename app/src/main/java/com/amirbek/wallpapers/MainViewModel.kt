package com.amirbek.wallpapers

import androidx.lifecycle.ViewModel
import com.amirbek.wallpapers.feature_settings.domain.SettingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	settingsDataStore: SettingsDataStore
) : ViewModel() {

	val isDarkTheme = settingsDataStore.isDarkTheme

}