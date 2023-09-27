package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Header(
	text: String,
	modifier: Modifier = Modifier
) {
	Column {
		Spacer(modifier = Modifier.height(16.dp))
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = modifier
		) {
			Spacer(modifier = Modifier.width(24.dp))
			Text(
				text = text,
				style = MaterialTheme.typography.headlineLarge.copy(
					fontWeight = FontWeight.SemiBold
				)
			)
		}
	}

}