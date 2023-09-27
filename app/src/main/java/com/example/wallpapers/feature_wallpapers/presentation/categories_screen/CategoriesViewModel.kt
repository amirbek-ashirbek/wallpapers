package com.example.wallpapers.feature_wallpapers.presentation.categories_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wallpapers.feature_wallpapers.domain.model.WallpaperCategory
import com.example.wallpapers.feature_wallpapers.domain.use_case.GetCategoriesPagedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
	private val getCategoriesPagedUseCase: GetCategoriesPagedUseCase
) : ViewModel() {

	private val _categories = MutableStateFlow<PagingData<WallpaperCategory>>(PagingData.empty())
	val categories: StateFlow<PagingData<WallpaperCategory>> = _categories

	init {
		getCategories()
	}

	fun onEvent() {

	}

	private fun getCategories() {
		viewModelScope.launch {
			getCategoriesPagedUseCase.execute()
				.cachedIn(viewModelScope)
				.collect { categoryPagingData ->
					_categories.value = categoryPagingData
				}
		}
	}

}

