package com.example.wallpapers.feature_wallpapers.presentation.categories_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.wallpapers.R
import com.example.wallpapers.feature_wallpapers.domain.model.WallpaperCategory
import com.example.wallpapers.feature_wallpapers.presentation.categories_screen.components.CategoriesList
import com.example.wallpapers.feature_wallpapers.presentation.common.Header
import com.example.wallpapers.feature_wallpapers.presentation.destinations.WallpapersScreenDestination
import com.example.wallpapers.feature_wallpapers.presentation.wallpapers_screen.WallpapersScreenNavArgs
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun CategoriesScreen(
	navigator: DestinationsNavigator
) {

	val categoriesViewModel: CategoriesViewModel = hiltViewModel()

	CategoriesScreenContent(
		categories = categoriesViewModel.categories.collectAsLazyPagingItems(),
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
			Header(
				text = stringResource(id = R.string.categories),
				needsBackButton = false,
				onBackClicked = {}
			)
			Spacer(modifier = Modifier.height(16.dp))
			CategoriesList(
				categories = categories,
				onCategoryClicked = onCategoryClicked
			)
		}
	}
}