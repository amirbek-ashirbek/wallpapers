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
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.WallpaperCategory
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.categories_screen.components.CategoryItem
import com.example.wallpapers.navigation.WallpapersScreen

@Composable
fun CategoriesScreen(
	viewModel: CategoriesViewModel = hiltViewModel(),
	navController: NavHostController
) {

	val categoriesLazyPagingItems: LazyPagingItems<WallpaperCategory> = viewModel.categoriesPaged.collectAsLazyPagingItems()

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
					count = categoriesLazyPagingItems.itemCount,
					key = categoriesLazyPagingItems.itemKey { wallpaper -> wallpaper.id },
					contentType = categoriesLazyPagingItems.itemContentType { "Categories" }
				) { index ->
					val category: WallpaperCategory? = categoriesLazyPagingItems[index]

					if (category != null) {
						CategoryItem(
							category = category,
							onItemClicked = {
								navController.navigate(WallpapersScreen.route + "/${category.id}")
							}
						)
						Spacer(modifier = Modifier.height(12.dp))
					}
				}
			}
		}
	}
}