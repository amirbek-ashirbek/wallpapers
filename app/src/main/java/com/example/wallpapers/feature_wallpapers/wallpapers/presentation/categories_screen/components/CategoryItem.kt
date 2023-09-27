package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.categories_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.common.GradientOverlay

@Composable
fun CategoryItem(
	title: String,
	coverPhotoUrl: String,
	onItemClicked: () -> Unit
) {

	val textHeight = remember { mutableIntStateOf(0) }

	Box(
		modifier = Modifier
			.clip(RoundedCornerShape(16.dp))
			.clickable(
				onClick = onItemClicked
			)
	) {
		AsyncImage(
			model = coverPhotoUrl,
			contentDescription = null,
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.fillMaxSize()
				.aspectRatio(19f / 10f)
		)
		GradientOverlay(
			height = textHeight.intValue.dp,
			alpha = 0.75f,
			modifier = Modifier
				.align(Alignment.BottomCenter)
		)
		Text(
			text = title,
			style = MaterialTheme.typography.titleMedium.copy(
				color = Color.White,
				fontWeight = FontWeight.Normal
			),
			modifier = Modifier
				.align(Alignment.BottomStart)
				.padding(start = 21.dp, bottom = 16.dp)
				.onGloballyPositioned { coordinates ->
					textHeight.intValue = coordinates.size.height
				}
		)
	}
}