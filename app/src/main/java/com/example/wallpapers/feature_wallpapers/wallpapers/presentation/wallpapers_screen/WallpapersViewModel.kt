package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.wallpapers.feature_wallpapers.wallpapers.data.repository.WallpaperRepository
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.Downloader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WallpapersViewModel @Inject constructor(
	private val wallpaperRepository: WallpaperRepository,
	private val downloader: Downloader,
	savedStateHandle: SavedStateHandle
) : ViewModel() {

	private val _uiState = MutableStateFlow(WallpapersState())
	val uiState: StateFlow<WallpapersState> = _uiState

	private val categoryId: String = savedStateHandle.get<String>("categoryId")!!

	val wallpapers = wallpaperRepository.getWallpapersByCategory(categoryId = categoryId)
		.cachedIn(viewModelScope)

	fun onEvent(event: WallpapersEvent) {
		when (event) {
			is WallpapersEvent.WallpaperClicked -> {
				_uiState.update {
					it.copy(
						isWallpaperVisibleInFullScreen = true,
						wallpaperInFullScreen = event.wallpaper
					)
				}
			}
			is WallpapersEvent.WallpaperDismissed -> {
				_uiState.update {
					it.copy(
						isWallpaperVisibleInFullScreen = false,
						wallpaperInFullScreen = null
					)
				}
			}
			is WallpapersEvent.DownloadClicked -> {
				_uiState.value.wallpaperInFullScreen?.downloadUrl?.let {
					downloader.downloadFile(url = it)
				}
			}
		}
	}

}