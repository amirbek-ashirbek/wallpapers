package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.single_wallpaper_screen.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wallpapers.R

@Composable
fun WallpaperHeader(
	text: String,
	onBackClicked: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = modifier
	) {
		Icon(
			painter = painterResource(id = R.drawable.icon_arrow_left),
			tint = Color.White,
			contentDescription = null,
			modifier = Modifier.clickable(
				onClick = onBackClicked
			)
		)
		Spacer(modifier = Modifier.width(12.dp))
		Text(
			text = text,
			style = MaterialTheme.typography.headlineLarge.copy(
				color = Color.White
			)
		)
	}
}