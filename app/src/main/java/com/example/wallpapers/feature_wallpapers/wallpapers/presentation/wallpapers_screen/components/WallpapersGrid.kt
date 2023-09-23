package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.Wallpaper

@Composable
fun WallpapersGrid(
	wallpapers: LazyPagingItems<Wallpaper>,
	onWallpaperClicked: (Wallpaper) -> Unit,
	modifier: Modifier = Modifier
) {
	LazyVerticalGrid(
		columns = GridCells.Fixed(2),
		contentPadding = PaddingValues(8.dp),
		modifier = modifier
			.fillMaxSize()
	) {

		items(
			count = wallpapers.itemCount,
			key = wallpapers.itemKey { wallpaper -> wallpaper.id },
			contentType = wallpapers.itemContentType { "Wallpaper" }
		) { index ->
			val wallpaper: Wallpaper? = wallpapers[index]

			if (wallpaper != null) {
				WallpaperItem(
					url = wallpaper.url,
					onItemClicked = { onWallpaperClicked(wallpaper) }
				)
			}
		}
	}
}