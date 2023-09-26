package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.Wallpaper
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.common.GradientOverlay

@Composable
fun WallpaperDialog(
	wallpaper: Wallpaper,
	onApplyClicked: () -> Unit,
	onDownloadClicked: () -> Unit,
	onFavouriteClicked: () -> Unit,
	onDismiss: () -> Unit
) {

	val actionsRowHeight = remember { mutableIntStateOf(0) }

	Dialog(
		onDismissRequest = onDismiss,
		properties = DialogProperties(
			usePlatformDefaultWidth = false,
			decorFitsSystemWindows = false
		)
	) {
		Surface(
			modifier = Modifier.fillMaxSize()
		) {
			Box(modifier = Modifier.fillMaxSize()) {
				AsyncImage(
					model = wallpaper.url,
					contentScale = ContentScale.Crop,
					contentDescription = "",
					modifier = Modifier
						.fillMaxSize()
				)
				GradientOverlay(
					height = actionsRowHeight.intValue.dp,
					alpha = 0.3f,
					modifier = Modifier
						.align(Alignment.BottomCenter)
				)
				WallpaperActionsRow(
					onDownloadClicked = onDownloadClicked,
					onApplyClicked = onApplyClicked,
					onFavouriteClicked = onFavouriteClicked,
					isFavourite = wallpaper.isFavourite,
					modifier = Modifier
						.align(Alignment.BottomCenter)
						.padding(bottom = 16.dp)
						.onGloballyPositioned { coordinates ->
							actionsRowHeight.intValue = coordinates.size.height
						}
				)
			}
		}
	}
}