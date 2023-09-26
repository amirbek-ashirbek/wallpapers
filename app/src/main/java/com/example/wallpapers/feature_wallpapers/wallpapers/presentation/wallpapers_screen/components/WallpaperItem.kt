package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wallpapers.R
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.common.GradientOverlay

@Composable
fun WallpaperItem(
	url: String,
	isFavourite: Boolean,
	onItemClicked: () -> Unit
) {

	val iconHeight = remember { mutableIntStateOf(0) }

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
				.aspectRatio(3f / 4f)
		)
		GradientOverlay(
			height = iconHeight.intValue.dp,
			alpha = 0.5f,
			modifier = Modifier
				.align(Alignment.BottomCenter)
		)
		Icon(
			painter = painterResource(
				id = if (isFavourite) R.drawable.icon_heart_full else R.drawable.icon_heart_empty
			),
			tint = Color.White,
			contentDescription = null,
			modifier = Modifier
				.size(40.dp)
				.align(Alignment.BottomEnd)
				.padding(bottom = 12.dp, end = 12.dp)
				.onGloballyPositioned { coordinates ->
					iconHeight.intValue = coordinates.size.height
				}
		)
	}
}