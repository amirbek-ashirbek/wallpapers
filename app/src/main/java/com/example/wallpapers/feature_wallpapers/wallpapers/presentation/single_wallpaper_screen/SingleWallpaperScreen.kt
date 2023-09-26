package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.single_wallpaper_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.wallpapers.R
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.WallpaperApplyScreen
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.common.GradientOverlay
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.common.ToggleVisibilityComponent
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.common.noRippleClickable
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.single_wallpaper_screen.components.ApplyDialog
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.single_wallpaper_screen.components.InternetErrorDialog
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.single_wallpaper_screen.components.WallpaperActionsRow
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.single_wallpaper_screen.components.WallpaperHeader
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(
	navArgsDelegate = SingleWallpaperScreenNavArgs::class
)
@Composable
fun SingleWallpaperScreen(
	navigator: DestinationsNavigator
) {
	val singleWallpaperViewModel: SingleWallpaperViewModel = hiltViewModel()

	SingleWallpaperScreenContent(
		uiState = singleWallpaperViewModel.uiState.collectAsStateWithLifecycle().value,
		onApplyClicked = { singleWallpaperViewModel.onEvent(SingleWallpaperEvent.ApplyButtonClicked) },
		onApplyConfirmed = { screen ->
			singleWallpaperViewModel.onEvent(SingleWallpaperEvent.WallpaperApplied(screen = screen))
		},
		onApplyDialogDismissed = { singleWallpaperViewModel.onEvent(SingleWallpaperEvent.ApplyDialogDismissed) },
		onDownloadClicked = { singleWallpaperViewModel.onEvent(SingleWallpaperEvent.DownloadClicked) },
		onInternetErrorDialogDismissed = { singleWallpaperViewModel.onEvent(SingleWallpaperEvent.InternetErrorDialogDismissed) },
		onFavouriteClicked = { singleWallpaperViewModel.onEvent(SingleWallpaperEvent.FavouriteClicked) },
		onBackClicked = { navigator.popBackStack() }
	)
}

@Composable
fun SingleWallpaperScreenContent(
	uiState: SingleWallpaperState,
	onApplyClicked: () -> Unit,
	onApplyConfirmed: (WallpaperApplyScreen) -> Unit,
	onApplyDialogDismissed: () -> Unit,
	onDownloadClicked: () -> Unit,
	onInternetErrorDialogDismissed: () -> Unit,
	onFavouriteClicked: () -> Unit,
	onBackClicked: () -> Unit
) {

	val isApplyDialogVisible = uiState.isApplyDialogVisible
	val internetError = uiState.internetError
	val wallpaperAppliedSuccessfully = uiState.wallpaperAppliedSuccessfully
	val context = LocalContext.current

	if (isApplyDialogVisible) {
		ApplyDialog(
			onApply = { screen ->
				onApplyConfirmed(screen)
			},
			onDismissRequest = onApplyDialogDismissed
		)
	}

	LaunchedEffect(context) {
		if (wallpaperAppliedSuccessfully) {
			Toast.makeText(
				context,
				context.getString(R.string.wallpaper_set_successfully),
				Toast.LENGTH_SHORT
			).show()
		}
	}

	if (internetError) {
		InternetErrorDialog(
			onDismissRequest = onInternetErrorDialogDismissed
		)
	}

	val actionsRowHeight = remember { mutableIntStateOf(0) }
	val headerHeight = remember { mutableIntStateOf(0) }
	var componentsVisible by remember { mutableStateOf(true) }

	Surface(
		modifier = Modifier
			.fillMaxSize()
			.noRippleClickable(
				onClick = {
					componentsVisible = !componentsVisible
				}
			)
	) {
		Box(modifier = Modifier.fillMaxSize()) {
			uiState.wallpaper?.let { wallpaper ->
				AsyncImage(
					model = wallpaper.url,
					contentScale = ContentScale.Crop,
					contentDescription = "",
					modifier = Modifier
						.fillMaxSize()
				)
				ToggleVisibilityComponent(
					visible = componentsVisible,
					content = {
						GradientOverlay(
							fromBottomToTop = false,
							height = headerHeight.intValue.dp,
							alpha = 0.5f,
						)
					},
					modifier = Modifier.align(Alignment.TopCenter)
				)
				ToggleVisibilityComponent(
					visible = componentsVisible,
					content = {
						WallpaperHeader(
							text = stringResource(id = R.string.wallpapers),
							onBackClicked = onBackClicked,
						)
					},
					modifier = Modifier
						.align(Alignment.TopStart)
						.padding(start = 12.dp, top = 32.dp)
						.onGloballyPositioned { coordinates ->
							headerHeight.intValue = coordinates.size.height
						}
				)
				ToggleVisibilityComponent(
					visible = componentsVisible,
					content = {
						GradientOverlay(
							height = actionsRowHeight.intValue.dp,
							alpha = 0.5f,
						)
					},
					modifier = Modifier.align(Alignment.BottomCenter)
				)
				ToggleVisibilityComponent(
					visible = componentsVisible,
					content = {
						WallpaperActionsRow(
							onDownloadClicked = onDownloadClicked,
							onApplyClicked = onApplyClicked,
							onFavouriteClicked = onFavouriteClicked,
							isFavourite = wallpaper.isFavourite,
						)
					},
					modifier = Modifier
						.align(Alignment.BottomCenter)
						.padding(bottom = 32.dp)
						.onGloballyPositioned { coordinates ->
							actionsRowHeight.intValue = coordinates.size.height
						}
				)
			}
		}
	}

}