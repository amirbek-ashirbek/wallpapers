package com.example.wallpapers.feature_wallpapers.presentation.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.wallpapers.feature_wallpapers.domain.model.WallpaperCategory

@Composable
fun CategoriesRow(
	categories: List<WallpaperCategory>,
	selectedCategoryId: String,
	onCategoryClicked: (WallpaperCategory) -> Unit
) {

	ScrollableTabRow(
		selectedTabIndex = categories.indexOfFirst { it.id == selectedCategoryId },
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
				selected = selectedCategoryId == category.id,
				onClick = {
					onCategoryClicked(category)
				},
				unselectedContentColor = MaterialTheme.colorScheme.secondary
			)
		}
	}

}