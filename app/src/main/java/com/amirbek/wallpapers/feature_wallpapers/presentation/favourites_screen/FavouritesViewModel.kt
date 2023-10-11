package com.amirbek.wallpapers.feature_wallpapers.presentation.favourites_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amirbek.wallpapers.feature_wallpapers.domain.model.Wallpaper
import com.amirbek.wallpapers.feature_wallpapers.domain.use_case.GetFavouriteWallpapersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
	private val getFavouriteWallpapersUseCase: GetFavouriteWallpapersUseCase
) : ViewModel() {

	private val _uiState = MutableStateFlow(FavouritesState())
	val uiState: StateFlow<FavouritesState> = _uiState

	val favouriteWallpapers: Flow<PagingData<Wallpaper>> = getFavouriteWallpapersUseCase.execute()
		.cachedIn(viewModelScope)

}