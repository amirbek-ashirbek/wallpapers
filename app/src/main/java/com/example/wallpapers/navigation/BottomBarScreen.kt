package com.example.wallpapers.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.wallpapers.R
import com.example.wallpapers.destinations.CategoriesScreenDestination
import com.example.wallpapers.destinations.FavouritesScreenDestination
import com.example.wallpapers.destinations.SettingsScreenDestination

sealed class BottomBarScreen(
	val route: String,
	@StringRes val label: Int,
	@DrawableRes val icon: Int
) {

	data object Categories : BottomBarScreen(
		route = CategoriesScreenDestination.route,
		label = R.string.categories,
		icon = R.drawable.icon_categories
	)

	data object Favourites : BottomBarScreen(
		route = FavouritesScreenDestination.route,
		label = R.string.favourites,
		icon = R.drawable.icon_heart_full
	)

	data object Settings : BottomBarScreen(
		route = SettingsScreenDestination.route,
		label = R.string.settings,
		icon = R.drawable.icon_settings
	)

}