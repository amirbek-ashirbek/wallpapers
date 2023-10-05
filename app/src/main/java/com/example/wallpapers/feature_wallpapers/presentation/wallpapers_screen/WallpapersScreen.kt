package com.example.wallpapers.feature_wallpapers.presentation.wallpapers_screen

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.wallpapers.R
import com.example.wallpapers.destinations.SingleWallpaperScreenDestination
import com.example.wallpapers.feature_wallpapers.domain.model.Wallpaper
import com.example.wallpapers.feature_wallpapers.domain.model.WallpaperCategory
import com.example.wallpapers.feature_wallpapers.presentation.common.CategoriesRow
import com.example.wallpapers.feature_wallpapers.presentation.common.Header
import com.example.wallpapers.feature_wallpapers.presentation.single_wallpaper_screen.SingleWallpaperScreenNavArgs
import com.example.wallpapers.feature_wallpapers.presentation.wallpapers_screen.components.WallpapersGrid
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
		headerTitle = stringResource(id = R.string.wallpapers),
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
		onFavouriteIconClicked = { wallpaper ->
			wallpapersViewModel.onEvent(WallpapersEvent.FavouriteIconClicked(wallpaper = wallpaper))
		},
		onCategoryClicked = { category ->
			wallpapersViewModel.onEvent(WallpapersEvent.CategoryItemClicked(category))
		},
		onBackClicked = { navigator.popBackStack() }
	)

}

@Composable
fun WallpapersScreenContent(
	wallpapers: LazyPagingItems<Wallpaper>,
	headerTitle: String,
	uiState: WallpapersState,
	onWallpaperClicked: (Wallpaper) -> Unit,
	onFavouriteIconClicked: (Wallpaper) -> Unit,
	onCategoryClicked: (WallpaperCategory) -> Unit,
	onBackClicked: () -> Unit
) {

	Surface(
		modifier = Modifier
			.fillMaxSize()
	) {
		Column {
			Header(
				text = headerTitle,
				needsBackButton = true,
				onBackClicked = onBackClicked
			)
			Spacer(modifier = Modifier.height(24.dp))
			uiState.categories?.let { categories ->
				CategoriesRow(
					categories = categories,
					selectedCategoryId = uiState.selectedCategoryId,
					onCategoryClicked = { category ->
						onCategoryClicked(category)
					}
				)
			}
			Spacer(modifier = Modifier.height(16.dp))
			WallpapersGrid(
				wallpapers = wallpapers,
				onWallpaperClicked = onWallpaperClicked,
				onFavouriteIconClicked = { wallpaper ->
					onFavouriteIconClicked(wallpaper)
				}
			)
		}
	}
}