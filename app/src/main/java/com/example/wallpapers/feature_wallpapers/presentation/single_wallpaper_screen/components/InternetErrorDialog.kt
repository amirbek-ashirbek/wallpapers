package com.example.wallpapers.feature_wallpapers.presentation.single_wallpaper_screen.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.wallpapers.R

@Composable
fun InternetErrorDialog(
	onDismissRequest: () -> Unit,
	modifier: Modifier = Modifier
) {
	AlertDialog(
		icon = {
			Icon(
				painter = painterResource(id = R.drawable.icon_no_internet),
				contentDescription = null
			)
		},
		title = {
			Text(
				text = stringResource(id = R.string.no_internet),
				style = MaterialTheme.typography.titleLarge
			)
		},
		text = {
		   Text(
			   text = stringResource(id = R.string.check_your_internet_connection),
			   style = MaterialTheme.typography.bodyLarge
		   )
		},
		onDismissRequest = onDismissRequest,
		confirmButton = {
			TextButton(
				onClick = onDismissRequest
			) {
				Text(
					text = stringResource(id = R.string.ok)
				)
			}
		}
	)

}