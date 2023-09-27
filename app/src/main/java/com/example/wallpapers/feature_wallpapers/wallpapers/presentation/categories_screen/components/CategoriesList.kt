package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.categories_screen.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.WallpaperCategory

@Composable
fun CategoriesList(
	categories: LazyPagingItems<WallpaperCategory>,
	onCategoryClicked: (String) -> Unit,
	modifier: Modifier = Modifier
) {
	LazyColumn(
		modifier = modifier
			.padding(horizontal = 12.dp)
	) {
		items(
			count = categories.itemCount,
			key = categories.itemKey { wallpaper -> wallpaper.id },
			contentType = categories.itemContentType { "Categories" }
		) { index ->
			val category: WallpaperCategory? = categories[index]

			if (category != null) {
				CategoryItem(
					title = category.title,
					coverPhotoUrl = category.coverPhotoUrl,
					onItemClicked = {
						onCategoryClicked(category.id)
					}
				)
				Spacer(modifier = Modifier.height(12.dp))
			}
		}
	}

}