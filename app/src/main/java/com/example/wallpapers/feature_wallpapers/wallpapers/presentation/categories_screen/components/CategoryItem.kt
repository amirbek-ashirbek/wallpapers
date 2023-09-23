package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.categories_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CategoryItem(
	title: String,
	coverPhotoUrl: String,
	onItemClicked: () -> Unit
) {
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
				.aspectRatio(5f/2f)
		)
		Text(
			text = title,
			color = Color.White,
			fontWeight = FontWeight.Bold,
			fontSize = 16.sp,
			modifier = Modifier
				.align(Alignment.BottomStart)
				.padding(start = 21.dp, bottom = 16.dp)
		)
	}
}