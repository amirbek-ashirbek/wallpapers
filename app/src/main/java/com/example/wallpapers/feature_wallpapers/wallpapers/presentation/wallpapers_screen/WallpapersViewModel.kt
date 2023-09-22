package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.wallpapers.feature_wallpapers.wallpapers.data.repository.WallpaperRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WallpapersViewModel @Inject constructor(
	private val wallpaperRepository: WallpaperRepository,
	savedStateHandle: SavedStateHandle
) : ViewModel() {

	private val _uiState = MutableStateFlow(WallpapersState())
	val uiState: StateFlow<WallpapersState> = _uiState

	private val categoryId: String = savedStateHandle.get<String>("categoryId")!!

	val wallpapers = wallpaperRepository.getWallpapersByCategory(categoryId = categoryId)
		.cachedIn(viewModelScope)

}