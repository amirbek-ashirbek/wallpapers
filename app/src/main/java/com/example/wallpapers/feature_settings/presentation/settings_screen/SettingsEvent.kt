package com.example.wallpapers.feature_settings.presentation.settings_screen

sealed class SettingsEvent {
	data class DarkModeToggled(val isDarkTheme: Boolean) : SettingsEvent()
}
