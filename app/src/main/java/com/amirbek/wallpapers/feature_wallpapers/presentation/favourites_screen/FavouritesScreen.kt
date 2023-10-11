package com.amirbek.wallpapers.feature_wallpapers.presentation.favourites_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.amirbek.wallpapers.R
import com.amirbek.wallpapers.destinations.SingleWallpaperScreenDestination
import com.amirbek.wallpapers.feature_wallpapers.domain.model.Wallpaper
import com.amirbek.wallpapers.feature_wallpapers.presentation.common.Header
import com.amirbek.wallpapers.feature_wallpapers.presentation.single_wallpaper_screen.SingleWallpaperScreenNavArgs
import com.amirbek.wallpapers.feature_wallpapers.presentation.wallpapers_screen.WallpapersState
import com.amirbek.wallpapers.feature_wallpapers.presentation.wallpapers_screen.components.WallpapersGrid
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun FavouritesScreen(
	navigator: DestinationsNavigator
) {

	val favouritesViewModel: FavouritesViewModel = hiltViewModel()

	FavouritesScreenContent(
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
		},
		onBackClicked = { }
	)
}

@Composable
fun FavouritesScreenContent(
	wallpapers: LazyPagingItems<Wallpaper>,
	headerTitle: String,
	uiState: WallpapersState?,
	onWallpaperClicked: (Wallpaper) -> Unit,
	onBackClicked: () -> Unit
) {

	Surface(
		modifier = Modifier
			.fillMaxSize()
	) {
		Column {
			Header(
				text = headerTitle,
				needsBackButton = false,
				onBackClicked = onBackClicked
			)
			Spacer(modifier = Modifier.height(16.dp))
			WallpapersGrid(
				wallpapers = wallpapers,
				onWallpaperClicked = onWallpaperClicked,
				onFavouriteIconClicked = {}
			)
		}
	}
}