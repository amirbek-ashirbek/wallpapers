package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.categories_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.WallpaperRepository
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.model.WallpaperCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
	wallpaperRepository: WallpaperRepository
) : ViewModel() {

	val categoriesPaged: Flow<PagingData<WallpaperCategory>> = wallpaperRepository.getAllWallpaperCategories()
		.cachedIn(viewModelScope)

	fun onEvent() {

	}

}

