package com.example.wallpapers.feature_wallpapers.wallpapers.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.WallpaperCategory

@Composable
fun WallpapersScreen(
	viewModel: WallpapersViewModel = hiltViewModel()
) {

//	val wallpapersLazyPagingItems: LazyPagingItems<Wallpaper> = viewModel.wallpapersPaged.collectAsLazyPagingItems()
	val categoriesLazyPagingItems: LazyPagingItems<WallpaperCategory> = viewModel.categoriesPaged.collectAsLazyPagingItems()

	Box(
		modifier = Modifier
			.fillMaxSize()
	) {

		LazyVerticalGrid(
			columns = GridCells.Fixed(2),
			modifier = Modifier
				.fillMaxSize()
				.padding(horizontal = 16.dp)
		) {

			items(
				count = categoriesLazyPagingItems.itemCount,
				key = categoriesLazyPagingItems.itemKey { wallpaper -> wallpaper.id },
				contentType = categoriesLazyPagingItems.itemContentType { "Categories" }
			) { index ->
				val category: WallpaperCategory? = categoriesLazyPagingItems[index]

				if (category != null) {
					Box {
						AsyncImage(
							model = category.coverPhotoUrl,
							contentDescription = null,
							contentScale = ContentScale.Crop,
							modifier = Modifier
								.fillMaxSize()
								.aspectRatio(1f)
								.padding(8.dp)
								.clip(RoundedCornerShape(8.dp))
						)
						Text(
							text = category.title,
							color = Color.White,
							modifier = Modifier
								.align(Alignment.Center)
						)
					}

				}
			}
		}
	}
}