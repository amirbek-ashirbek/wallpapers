package com.example.wallpapers.feature_wallpapers.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun GradientOverlay(
	fromBottomToTop: Boolean = true,
	height: Dp,
	alpha: Float,
	modifier: Modifier = Modifier
) {
	val colors = if (fromBottomToTop) {
		listOf(
			Color.Black.copy(alpha = 0f),
			Color.Black.copy(alpha = alpha)
		)
	} else {
		listOf(
			Color.Black.copy(alpha = alpha),
			Color.Black.copy(alpha = 0f)
		)
	}
	Box(
		modifier = modifier
			.fillMaxWidth()
			.height(height = height)
			.background(
				brush = Brush.verticalGradient(
					colors = colors
				)
			)
	)
}