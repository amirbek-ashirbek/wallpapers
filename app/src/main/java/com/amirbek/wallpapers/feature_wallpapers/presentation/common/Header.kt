package com.amirbek.wallpapers.feature_wallpapers.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.amirbek.wallpapers.R

@Composable
fun Header(
	text: String,
	needsBackButton: Boolean,
	onBackClicked: () -> Unit,
	modifier: Modifier = Modifier
) {
	Column {
		Spacer(modifier = Modifier.height(16.dp))
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = modifier
		) {
			if (needsBackButton) {
				Spacer(modifier = Modifier.width(12.dp))
				Icon(
					painter = painterResource(id = R.drawable.icon_arrow_left),
					contentDescription = null,
					modifier = Modifier.noRippleClickable(
						onClick = onBackClicked
					)
				)
			}
			Spacer(modifier = Modifier.width(24.dp))
			Text(
				text = text,
				style = MaterialTheme.typography.headlineLarge.copy(
					color = MaterialTheme.colorScheme.primary,
					fontWeight = FontWeight.SemiBold
				)
			)
		}
	}

}