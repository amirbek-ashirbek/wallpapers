package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wallpapers.R

@Composable
fun WallpaperItem(
	url: String,
	onItemClicked: () -> Unit
) {

	Box(
		modifier = Modifier
			.padding(8.dp)
			.clip(RoundedCornerShape(8.dp))
			.clickable(
				onClick = onItemClicked
			)
	) {
		AsyncImage(
			model = url,
			placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
			contentDescription = null,
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.fillMaxSize()
				.aspectRatio(9f / 16f)
		)
	}
}