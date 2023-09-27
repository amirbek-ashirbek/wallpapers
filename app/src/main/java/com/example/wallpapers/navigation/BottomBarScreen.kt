package com.example.wallpapers.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.wallpapers.R
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.destinations.CategoriesScreenDestination
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.destinations.FavouritesScreenDestination

//enum class BottomBarDestination(
//	val route: String,
//	@DrawableRes val icon: Int,
//	@StringRes val label: Int
//) {
//	Categories(CategoriesScreenDestination.route, R.drawable.icon_categories, R.string.categories),
//	Favourites(FavouritesScreenDestination.route, R.drawable.icon_heart_full, R.string.favourites)
//}

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

}