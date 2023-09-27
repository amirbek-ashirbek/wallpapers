package com.example.wallpapers.feature_wallpapers.presentation.single_wallpaper_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapers.feature_wallpapers.domain.model.Wallpaper
import com.example.wallpapers.feature_wallpapers.domain.use_case.DownloadWallpaperUseCase
import com.example.wallpapers.feature_wallpapers.domain.use_case.GetSingleWallpaperUseCase
import com.example.wallpapers.feature_wallpapers.domain.use_case.UpdateWallpaperIsFavouriteUseCase
import com.example.wallpapers.feature_wallpapers.presentation.WallpaperApplyScreen
import com.example.wallpapers.feature_wallpapers.presentation.WallpaperSetter
import com.example.wallpapers.feature_wallpapers.presentation.navArgs
import com.example.wallpapers.util.DownloadResult
import com.example.wallpapers.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleWallpaperViewModel @Inject constructor(
	private val downloadWallpaperUseCase: DownloadWallpaperUseCase,
	private val updateWallpaperIsFavouriteUseCase: UpdateWallpaperIsFavouriteUseCase,
	private val getSingleWallpaperUseCase: GetSingleWallpaperUseCase,
	private val wallpaperSetter: WallpaperSetter,
	savedStateHandle: SavedStateHandle
) : ViewModel() {

	private val _uiState = MutableStateFlow(SingleWallpaperState())
	val uiState: StateFlow<SingleWallpaperState> = _uiState

	private val navArgs: SingleWallpaperScreenNavArgs = savedStateHandle.navArgs()

	init {
		getWallpaper(wallpaperId = navArgs.wallpaperId)
	}

	fun onEvent(event: SingleWallpaperEvent) {
		when (event) {
			is SingleWallpaperEvent.ApplyButtonClicked -> {
				_uiState.update { it.copy(isApplyDialogVisible = true) }
			}
			is SingleWallpaperEvent.ApplyDialogDismissed -> {
				_uiState.update { it.copy(isApplyDialogVisible = false) }
			}
			is SingleWallpaperEvent.DownloadClicked -> {
				_uiState.value.wallpaper?.let {
					downloadWallpaper(
						url = it.url,
						wallpaperId = it.id
					)
				}
			}
			is SingleWallpaperEvent.FavouriteClicked -> {
				_uiState.value.wallpaper?.let {
					updateWallpaperIsFavourite(wallpaper = it)
				}
			}
			is SingleWallpaperEvent.InternetErrorDialogDismissed -> {
				_uiState.update { it.copy(internetError = false) }
			}
			is SingleWallpaperEvent.WallpaperApplied -> {
				_uiState.value.wallpaper?.let {
					setWallpaper(
						url = it.url,
						screen = event.screen
					)
				}
			}
		}
	}

	private fun getWallpaper(wallpaperId: String) {
		viewModelScope.launch {
			getSingleWallpaperUseCase.execute(wallpaperId = wallpaperId).collect { wallpaper ->
				_uiState.update { it.copy(wallpaper = wallpaper) }
			}
		}
	}

	private fun setWallpaper(url: String, screen: WallpaperApplyScreen) {
		_uiState.update { it.copy(isWallpaperApplying = true) }
		viewModelScope.launch {
			val result = wallpaperSetter.setWallpaper(url = url, screen = screen)
			when (result) {
				is Result.Success -> {
					_uiState.update {
						it.copy(
							wallpaperAppliedSuccessfully = true,
							isWallpaperApplying = false,
							isApplyDialogVisible = false
						)
					}
				}
				is Result.Failure -> {
					_uiState.update {
						it.copy(
							wallpaperAppliedSuccessfully = false,
							isWallpaperApplying = false
						)
					}
				}
			}
		}
	}

	private fun downloadWallpaper(url: String, wallpaperId: String) {
		_uiState.update { it.copy(isDownloading = true) }

		viewModelScope.launch {
			val result = downloadWallpaperUseCase.execute(url = url, wallpaperId = wallpaperId)
			when (result) {
				is DownloadResult.Success -> {
					_uiState.update { it.copy(isDownloading = false) }
				}

				is DownloadResult.InternetError -> {
					_uiState.update { it.copy(internetError = true, isDownloading = false) }
				}

				is DownloadResult.OtherError -> {
					_uiState.update { it.copy(isDownloading = false) }
				}
			}
		}
	}

	private fun updateWallpaperIsFavourite(wallpaper: Wallpaper) {
		viewModelScope.launch {
			val updatedWallpaper = wallpaper.copy(isFavourite = !wallpaper.isFavourite)
			updateWallpaperIsFavouriteUseCase.execute(wallpaper = updatedWallpaper)
		}
	}

}