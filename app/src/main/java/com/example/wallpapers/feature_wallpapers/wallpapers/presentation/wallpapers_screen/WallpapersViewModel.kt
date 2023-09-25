package com.example.wallpapers.feature_wallpapers.wallpapers.presentation.wallpapers_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.wallpapers.feature_wallpapers.wallpapers.data.repository.WallpaperRepository
import com.example.wallpapers.feature_wallpapers.wallpapers.domain.use_case.DownloadWallpaperUseCase
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.WallpaperApplyScreen
import com.example.wallpapers.feature_wallpapers.wallpapers.presentation.WallpaperSetter
import com.example.wallpapers.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallpapersViewModel @Inject constructor(
	private val wallpaperRepository: WallpaperRepository,
	private val downloadWallpaperUseCase: DownloadWallpaperUseCase,
	private val wallpaperSetter: WallpaperSetter,
	savedStateHandle: SavedStateHandle,
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
				_uiState.value.wallpaperInFullScreen?.let { wallpaper ->
					downloadWallpaper(url = wallpaper.url, wallpaperId = wallpaper.id)
				}
			}
			is WallpapersEvent.ApplyButtonClicked -> {
				_uiState.update { it.copy(isApplyDialogVisible = true) }

			}
			is WallpapersEvent.WallpaperApplied -> {
				_uiState.value.wallpaperInFullScreen?.url?.let {
					setWallpaper(
						url = it,
						screen = event.screen
					)
				}
			}
			is WallpapersEvent.ApplyDialogDismissed -> {
				_uiState.update { it.copy(isApplyDialogVisible = false) }
			}
		}
	}

	private fun setWallpaper(url: String, screen: WallpaperApplyScreen) {
		_uiState.update { it.copy(isWallpaperApplying = true) }
		viewModelScope.launch {
			val result = wallpaperSetter.setWallpaper(url = url, screen = screen)
			when (result) {
				is Result.Success -> {
					_uiState.update { it.copy(
						wallpaperAppliedSuccessfully = true,
						isWallpaperApplying = false,
						isApplyDialogVisible = false
					) }
				}
				is Result.Failure -> {
					_uiState.update { it.copy(
						wallpaperAppliedSuccessfully = false,
						isWallpaperApplying = false
					) }
				}
			}
		}
	}

	// TODO function needs improving (loading and error handling)
	private fun downloadWallpaper(url: String, wallpaperId: String) {
		val downloadId = downloadWallpaperUseCase.execute(
			url = url,
			wallpaperId = wallpaperId
		)
		if (downloadId == -1L) {
			Log.d("WallpaperDownload", "erroor")
			_uiState.update { it.copy(downloadError = true) }
		} else {
			Log.d("WallpaperDownload", "it's okay")
			_uiState.update { it.copy(downloadError = false) }
		}
	}

}