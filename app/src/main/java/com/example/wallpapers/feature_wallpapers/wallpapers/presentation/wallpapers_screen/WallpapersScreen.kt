package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.Wallpaper
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen.components.ApplyDialog
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
	val isApplyDialogVisible: Boolean = uiState.isApplyDialogVisible

	AnimatedVisibility(
		visible = isWallpaperVisibleInFullScreen,
		enter = expandIn(),
		exit = shrinkOut()
	) {
		wallpaperInFullScreen?.let { wallpaper ->
			WallpaperDialog(
				url = wallpaper.url,
				onDismiss = { onWallpapersEvent(WallpapersEvent.WallpaperDismissed) },
				onApplyClicked = { onWallpapersEvent(WallpapersEvent.ApplyClicked(wallpaper = wallpaper)) },
				onDownloadClicked = { onWallpapersEvent(WallpapersEvent.DownloadClicked(wallpaper = wallpaper)) }
			)
		}
	}
	
	if (isApplyDialogVisible) {
		ApplyDialog(
			onDismissRequest = { onWallpapersEvent(WallpapersEvent.ApplyDialogDismissed) }
		)
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
