package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
	onButtonClicked: () -> Unit,
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
				Row(
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.SpaceBetween,
					modifier = Modifier
						.align(Alignment.BottomCenter)
						.fillMaxWidth()
						.padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
				) {
					Button(
						onClick = onButtonClicked,
						modifier = Modifier
					) {
						Text(
							text = "Apply"
						)
					}
					Button(
						onClick = { },
						modifier = Modifier
					) {
						Text(
							text = "Download"
						)
					}
					Button(
						onClick = { },
						modifier = Modifier
					) {
						Text(
							text = "Favourite"
						)
					}
				}
			}
		}
	}
}