package com.amirbek.wallpapers.feature_settings.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.amirbek.wallpapers.feature_settings.domain.SettingsDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsDataStoreImpl @Inject constructor(
	context: Context
) : SettingsDataStore {

	private val dataStore = context.settingsDataStore

	override val isDarkTheme: Flow<Boolean?> = dataStore.data.map { preferences ->
		preferences[KEY_IS_DARK_THEME]
	}

	override suspend fun saveDarkTheme(isDarkTheme: Boolean) {
		dataStore.edit { preferences ->
			preferences[KEY_IS_DARK_THEME] = isDarkTheme
		}
	}

	companion object {
		private val KEY_IS_DARK_THEME = booleanPreferencesKey("key_is_dark_theme")
	}

}