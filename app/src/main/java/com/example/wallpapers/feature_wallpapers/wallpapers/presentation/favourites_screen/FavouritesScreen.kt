package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.favourites_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.wallpapers.R
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.destinations.SingleWallpaperScreenDestination
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.single_wallpaper_screen.SingleWallpaperScreenNavArgs
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen.WallpapersScreenContent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun FavouritesScreen(
	navigator: DestinationsNavigator
) {

	val favouritesViewModel: FavouritesViewModel = hiltViewModel()

	WallpapersScreenContent(
		wallpapers = favouritesViewModel.favouriteWallpapers.collectAsLazyPagingItems(),
		uiState = null,
		headerTitle = stringResource(id = R.string.favourites),
		onWallpaperClicked = { wallpaper ->
			navigator.navigate(
				SingleWallpaperScreenDestination(
					navArgs = SingleWallpaperScreenNavArgs(
						wallpaperId = wallpaper.id,
						url = wallpaper.url,
						isFavourite = wallpaper.isFavourite
					)
				)
			)
		}
	)
}