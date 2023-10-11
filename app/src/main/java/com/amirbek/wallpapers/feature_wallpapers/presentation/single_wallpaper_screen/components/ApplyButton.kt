package com.amirbek.wallpapers.feature_wallpapers.presentation.single_wallpaper_screen.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ApplyButton(
	onClick: () -> Unit,
	text: String,
	modifier: Modifier = Modifier
) {

	Button(
		shape = RoundedCornerShape(8.dp),
		onClick = onClick,
		modifier = modifier
			.height(56.dp)
	) {
		Text(
			text = text
		)
	}

}