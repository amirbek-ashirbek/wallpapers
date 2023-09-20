package com.example.wallpapers.feature_wallpapers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wallpapers.feature_wallpapers.data.repository.WallpaperRepository
import com.example.wallpapers.feature_wallpapers.domain.model.Wallpaper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class WallpapersViewModel @Inject constructor(
	wallpaperRepository: WallpaperRepository
) : ViewModel() {

	val wallpapersPaged: Flow<PagingData<Wallpaper>> = wallpaperRepository.getAllWallpapers()
		.cachedIn(viewModelScope)

}

