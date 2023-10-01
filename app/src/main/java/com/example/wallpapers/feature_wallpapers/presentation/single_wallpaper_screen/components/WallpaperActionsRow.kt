package com.example.wallpapers.feature_wallpapers.presentation.single_wallpaper_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpapers.R

@Composable
fun WallpaperActionsRow(
	onDownloadClicked: () -> Unit,
	onApplyClicked: () -> Unit,
	onFavouriteClicked: () -> Unit,
	isFavourite: Boolean,
	modifier: Modifier = Modifier
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween,
		modifier = modifier
			.fillMaxWidth()
	) {
		ActionItem(
			imageResId = R.drawable.icon_download,
			title = stringResource(id = R.string.download),
			onClick = onDownloadClicked
		)
		ActionItem(
			imageResId = R.drawable.icon_apply,
			title = stringResource(id = R.string.apply),
			onClick = onApplyClicked
		)
		ActionItem(
			imageResId = if (isFavourite) R.drawable.icon_heart_full else R.drawable.icon_heart_empty,
			title = stringResource(id = R.string.favourite),
			onClick = onFavouriteClicked
		)
	}
}

@Composable
fun ActionItem(
	imageResId: Int,
	title: String,
	onClick: () -> Unit,
	modifier: Modifier = Modifier
) {

	Box(
		contentAlignment = Alignment.Center,
		modifier = modifier
			.height(72.dp)
			.clip(RoundedCornerShape(16.dp))
			.clickable { onClick() }
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			Icon(
				painter = painterResource(id = imageResId),
				tint = Color.White,
				contentDescription = null,
				modifier = Modifier.size(32.dp)
			)
			Spacer(modifier = Modifier.height(4.dp))
			Text(
				text = title,
				color = Color.White,
				fontSize = 12.sp
			)
		}
	}

}