package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.Wallpaper

@Composable
fun WallpapersScreen(
	wallpapers: LazyPagingItems<Wallpaper>,
	uiState: WallpapersState
) {

	Surface(
		modifier = Modifier
			.fillMaxSize()
	) {
		LazyVerticalGrid(
			columns = GridCells.Fixed(2),
			contentPadding = PaddingValues(8.dp),
			modifier = Modifier
				.fillMaxSize()
		) {

			items(
				count = wallpapers.itemCount,
				key = wallpapers.itemKey { wallpaper -> wallpaper.id },
				contentType = wallpapers.itemContentType { "Wallpaper" }
			) { index ->
				val wallpaper: Wallpaper? = wallpapers[index]

				if (wallpaper != null) {
					Box(
						modifier = Modifier
							.padding(8.dp)
							.clip(RoundedCornerShape(8.dp))
							.clickable(
								onClick = {

								}
							)
					) {
						AsyncImage(
							model = wallpaper.url,
							contentDescription = null,
							contentScale = ContentScale.Crop,
							modifier = Modifier
								.fillMaxSize()
								.aspectRatio(1f)
						)
					}

				}
			}
		}
	}

}
