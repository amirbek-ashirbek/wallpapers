package com.example.wallpapers.feature_wallpapers.presentation.wallpapers_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wallpapers.feature_wallpapers.domain.model.Wallpaper
import com.example.wallpapers.feature_wallpapers.domain.use_case.GetCategoriesUseCase
import com.example.wallpapers.feature_wallpapers.domain.use_case.GetWallpapersByCategoryUseCase
import com.example.wallpapers.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallpapersViewModel @Inject constructor(
	private val getWallpapersByCategoryUseCase: GetWallpapersByCategoryUseCase,
	private val getCategoriesUseCase: GetCategoriesUseCase,
	savedStateHandle: SavedStateHandle,
) : ViewModel() {

	private val navArgs: WallpapersScreenNavArgs = savedStateHandle.navArgs()
	private val initialCategoryId = navArgs.categoryId

	private val _uiState = MutableStateFlow(WallpapersState(selectedCategoryId = initialCategoryId))
	val uiState: StateFlow<WallpapersState> = _uiState

	private val _wallpapers = MutableStateFlow<PagingData<Wallpaper>>(PagingData.empty())
	val wallpapers: StateFlow<PagingData<Wallpaper>> = _wallpapers

	init {
		getCategories()
		getWallpapersByCategory(categoryId = initialCategoryId)
	}

	fun onEvent(event: WallpapersEvent) {
		when (event) {
			is WallpapersEvent.CategoryItemClicked -> {
				_uiState.update { it.copy(selectedCategoryId = event.category.id) }
				getWallpapersByCategory(categoryId = event.category.id)
			}
		}
	}

	private fun getWallpapersByCategory(categoryId: String) {
		viewModelScope.launch {
			getWallpapersByCategoryUseCase.execute(categoryId = categoryId)
				.cachedIn(viewModelScope)
				.collect { wallpaperPagingData ->
				_wallpapers.value = wallpaperPagingData
			}
		}
	}

	private fun getCategories() {
		viewModelScope.launch {
			getCategoriesUseCase.execute().collect { categories ->
				_uiState.update { it.copy(categories = categories) }
			}
		}
	}

}