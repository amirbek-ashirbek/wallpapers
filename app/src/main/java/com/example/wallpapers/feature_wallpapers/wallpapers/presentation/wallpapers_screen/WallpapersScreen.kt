package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.paging.compose.LazyPagingItems
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.Wallpaper
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen.components.WallpaperDialog
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen.components.WallpapersGrid

@Composable
fun WallpapersScreen(
	wallpapers: LazyPagingItems<Wallpaper>,
	uiState: WallpapersState,
	onWallpapersEvent: (WallpapersEvent) -> Unit
) {

	val isWallpaperVisibleInFullScreen: Boolean = uiState.isWallpaperVisibleInFullScreen
	val wallpaperInFullScreen: Wallpaper? = uiState.wallpaperInFullScreen

	val testDialogIsVisible = remember { mutableStateOf(false) }

//	if (isWallpaperVisibleInFullScreen) {
//
//	}

	AnimatedVisibility(
		visible = isWallpaperVisibleInFullScreen,
		enter = expandIn(),
		exit = shrinkOut()
	) {
		wallpaperInFullScreen?.url?.let { url ->
			WallpaperDialog(
				url = url,
				onDismiss = { onWallpapersEvent(WallpapersEvent.WallpaperDismissed) },
				onButtonClicked = { testDialogIsVisible.value = true },
				onDownloadClicked = { onWallpapersEvent(WallpapersEvent.DownloadClicked(uiState.wallpaperInFullScreen)) }
			)
		}
	}

	if (testDialogIsVisible.value) {
		Dialog(
			onDismissRequest = { testDialogIsVisible.value = false }
		) {
			Box(
				modifier = Modifier
					.size(200.dp)
					.background(color = Color.Yellow)
			) {
				Text(text = "yoooo")
			}
		}
	}

	Surface(
		modifier = Modifier
			.fillMaxSize()
	) {
		WallpapersGrid(
			wallpapers = wallpapers,
			onWallpaperClicked = { wallpaper ->
				onWallpapersEvent(WallpapersEvent.WallpaperClicked(wallpaper))
			}
		)
	}

}
