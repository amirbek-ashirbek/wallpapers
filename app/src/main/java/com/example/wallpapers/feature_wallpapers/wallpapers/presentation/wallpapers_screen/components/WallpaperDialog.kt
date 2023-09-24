package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage

@Composable
fun WallpaperDialog(
	url: String,
	onApplyClicked: () -> Unit,
	onDownloadClicked: () -> Unit,
	onDismiss: () -> Unit
) {
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
					model = url,
//					contentScale = ContentScale.FillBounds,
					contentDescription = "",
					modifier = Modifier
						.fillMaxSize()
				)
				WallpaperActionsRow(
					onDownloadClicked = onDownloadClicked,
					onApplyClicked = onApplyClicked,
					modifier = Modifier
						.align(Alignment.BottomCenter)
						.padding(bottom = 16.dp)
				)
			}
		}
	}
}