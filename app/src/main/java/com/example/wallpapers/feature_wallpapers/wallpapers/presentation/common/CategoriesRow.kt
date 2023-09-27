package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.WallpaperCategory

@Composable
fun CategoriesRow(
	categories: List<WallpaperCategory>,
	onCategoryClicked: (WallpaperCategory) -> Unit
) {

	var tabIndex by remember { mutableIntStateOf(0) }
	
	ScrollableTabRow(
		selectedTabIndex = tabIndex,
		edgePadding = 32.dp
	) {
		categories.forEachIndexed { index, category ->
			Tab(
				text = {
					Text(
						text = category.title,
						style = MaterialTheme.typography.titleMedium
					)
				},
				selected = tabIndex == index,
				onClick = {
					tabIndex = index
					onCategoryClicked(category)
				},
			)
		}
	}

}