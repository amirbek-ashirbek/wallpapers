package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.categories_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.WallpaperCategory
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.categories_screen.components.CategoryItem
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.destinations.WallpapersScreenDestination
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen.WallpapersScreenNavArgs
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun CategoriesScreen(
	navigator: DestinationsNavigator
) {

	val categoriesViewModel: CategoriesViewModel = hiltViewModel()

	CategoriesScreenContent(
		categories = categoriesViewModel.categoriesPaged.collectAsLazyPagingItems(),
		onCategoryClicked = { categoryId ->
			navigator.navigate(
				WallpapersScreenDestination(
					navArgs = WallpapersScreenNavArgs(categoryId = categoryId)
				)
			)
		}
	)

}

@Composable
fun CategoriesScreenContent(
	categories: LazyPagingItems<WallpaperCategory>,
	onCategoryClicked: (String) -> Unit
) {

	Surface(
		color = MaterialTheme.colorScheme.surface,
		modifier = Modifier
			.fillMaxSize()
	) {
		Column {
			Spacer(modifier = Modifier.height(32.dp))

			LazyColumn(
				modifier = Modifier
					.fillMaxSize()
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
	}
}