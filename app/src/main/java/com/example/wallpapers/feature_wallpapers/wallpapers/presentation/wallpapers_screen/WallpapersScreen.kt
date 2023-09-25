package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.LazyPagingItems
import com.example.wallpapers.R
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

	val context = LocalContext.current

	AnimatedVisibility(
		visible = isWallpaperVisibleInFullScreen,
		enter = expandIn(),
		exit = shrinkOut()
	) {
		wallpaperInFullScreen?.let { wallpaper ->
			WallpaperDialog(
				url = wallpaper.url,
				onDismiss = { onWallpapersEvent(WallpapersEvent.WallpaperDismissed) },
				onApplyClicked = { onWallpapersEvent(WallpapersEvent.ApplyButtonClicked(wallpaper = wallpaper)) },
				onDownloadClicked = { onWallpapersEvent(WallpapersEvent.DownloadClicked(wallpaper = wallpaper)) }
			)
		}
	}
	
	if (isApplyDialogVisible) {
		ApplyDialog(
			onApply = { screen ->
				onWallpapersEvent(WallpapersEvent.WallpaperApplied(screen = screen))
			},
			onDismissRequest = { onWallpapersEvent(WallpapersEvent.ApplyDialogDismissed) }
		)
	}

	LaunchedEffect(context) {
		if (uiState.wallpaperAppliedSuccessfully) {
			Toast.makeText(
				context,
				context.getString(R.string.wallpaper_set_successfully),
				Toast.LENGTH_SHORT
			).show()
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