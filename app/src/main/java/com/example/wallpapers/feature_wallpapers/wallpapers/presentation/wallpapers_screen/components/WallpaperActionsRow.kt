package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpapers.R

@Composable
fun WallpaperActionsRow(
	onDownloadClicked: () -> Unit,
	onApplyClicked: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = modifier
	) {
		ActionItem(
			imageResId = R.drawable.icon_download,
			title = "Download",
			onClick = onDownloadClicked
		)
		Spacer(modifier = Modifier.width(64.dp))
		ActionItem(
			imageResId = R.drawable.icon_apply,
			title = "Apply",
			onClick = onApplyClicked
		)
		Spacer(modifier = Modifier.width(64.dp))
		ActionItem(
			imageResId = R.drawable.icon_heart_empty,
			title = "Favourites",
			onClick = {  }
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

	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier.clickable { onClick() }
	) {
		Image(
			painter = painterResource(id = imageResId),
			colorFilter = ColorFilter.tint(color = Color(0xFFA3159D)),
			contentDescription = null,
		)
		Spacer(modifier = Modifier.height(4.dp))
		Text(
			text = title,
			fontSize = 12.sp
		)
	}

}