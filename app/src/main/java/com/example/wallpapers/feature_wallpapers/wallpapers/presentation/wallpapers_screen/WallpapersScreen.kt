package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.Wallpaper
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.destinations.SingleWallpaperScreenDestination
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.single_wallpaper_screen.SingleWallpaperScreenNavArgs
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen.components.WallpapersGrid
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(
	navArgsDelegate = WallpapersScreenNavArgs::class
)
@Composable
fun WallpapersScreen(
	navigator: DestinationsNavigator
) {

	val wallpapersViewModel: WallpapersViewModel = hiltViewModel()

	WallpapersScreenContent(
		wallpapers = wallpapersViewModel.wallpapers.collectAsLazyPagingItems(),
		uiState = wallpapersViewModel.uiState.collectAsStateWithLifecycle().value,
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

@Composable
fun WallpapersScreenContent(
	wallpapers: LazyPagingItems<Wallpaper>,
	uiState: WallpapersState,
	onWallpaperClicked: (Wallpaper) -> Unit
) {

	Surface(
		modifier = Modifier
			.fillMaxSize()
	) {
		WallpapersGrid(
			wallpapers = wallpapers,
			onWallpaperClicked = onWallpaperClicked
		)
	}

}