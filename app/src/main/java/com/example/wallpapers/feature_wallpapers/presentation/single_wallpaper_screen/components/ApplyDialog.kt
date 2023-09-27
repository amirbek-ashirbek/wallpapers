package com.example.wallpapers.feature_wallpapers.presentation.single_wallpaper_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.wallpapers.R
import com.example.wallpapers.feature_wallpapers.presentation.WallpaperApplyScreen

@Composable
fun ApplyDialog(
	onApply: (WallpaperApplyScreen) -> Unit,
	onDismissRequest: () -> Unit
) {
	Dialog(onDismissRequest = onDismissRequest) {
		Card(
			shape = RoundedCornerShape(24.dp),
			modifier = Modifier
				.fillMaxWidth()
		) {
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 24.dp)
			) {
				Spacer(modifier = Modifier.height(16.dp))
				Icon(
					painter = painterResource(id = R.drawable.icon_apply),
					contentDescription = null
				)
				Spacer(modifier = Modifier.height(8.dp))
				Text(
					text = stringResource(id = R.string.apply_wallpaper_on),
					style = MaterialTheme.typography.titleLarge
				)
				Spacer(modifier = Modifier.height(24.dp))
				ApplyButton(
					onClick = { onApply(WallpaperApplyScreen.HOME_SCREEN) },
					text = stringResource(id = R.string.home_screen),
					modifier = Modifier.fillMaxWidth()
				)
				Spacer(modifier = Modifier.height(4.dp))
				ApplyButton(
					onClick = { onApply(WallpaperApplyScreen.LOCK_SCREEN) },
					text = stringResource(id = R.string.lock_screen),
					modifier = Modifier.fillMaxWidth()
				)
				Spacer(modifier = Modifier.height(4.dp))
				ApplyButton(
					onClick = { onApply(WallpaperApplyScreen.HOME_AND_LOCK_SCREENS) },
					text = stringResource(id = R.string.home_and_lock_screens),
					modifier = Modifier.fillMaxWidth()
				)
				Spacer(modifier = Modifier.height(24.dp))
			}
		}
	}
}