package com.example.wallpapers.feature_wallpapers.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.example.wallpapers.feature_wallpapers.domain.model.Wallpaper

@Composable
fun WallpapersScreen(
	viewModel: WallpapersViewModel = hiltViewModel()
) {

	val wallpapersLazyPagingItems: LazyPagingItems<Wallpaper> = viewModel.wallpapersPaged.collectAsLazyPagingItems()

	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(color = Color.Red)
	) {

		LazyColumn(
			modifier = Modifier
				.fillMaxSize()
				.padding(horizontal = 16.dp)
		) {

			items(
				count = wallpapersLazyPagingItems.itemCount,
				key = wallpapersLazyPagingItems.itemKey { wallpaper -> wallpaper.id },
				contentType = wallpapersLazyPagingItems.itemContentType { "Wallpapers" }
			) { index ->
				val wallpaper: Wallpaper? = wallpapersLazyPagingItems[index]

				if (wallpaper != null) {
					AsyncImage(
						model = wallpaper.url,
						contentDescription = null,
						modifier = Modifier.padding(vertical = 16.dp)
					)
				}
			}

		}

	}
}