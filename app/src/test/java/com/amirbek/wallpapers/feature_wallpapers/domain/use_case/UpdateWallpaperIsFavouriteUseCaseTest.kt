package com.amirbek.wallpapers.feature_wallpapers.domain.use_case

import com.amirbek.wallpapers.feature_wallpapers.domain.model.Wallpaper
import com.amirbek.wallpapers.feature_wallpapers.domain.repository.WallpaperRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UpdateWallpaperIsFavouriteUseCaseTest {

	private lateinit var wallpaperRepository: WallpaperRepository
	private lateinit var updateWallpaperIsFavouriteUseCase: UpdateWallpaperIsFavouriteUseCase

	@Before
	fun setUp() {
		wallpaperRepository = mockk<WallpaperRepository>()
		updateWallpaperIsFavouriteUseCase = UpdateWallpaperIsFavouriteUseCase(wallpaperRepository = wallpaperRepository)
	}

	@Test
	fun `execute calls repository function with correctly updated wallpaper`() = runTest {

		// Arrange
		val wallpaper = Wallpaper("id", "categoryId","url","downloadUrl", isFavourite = false)
		val wallpaperSlot = slot<Wallpaper>()
		coEvery { wallpaperRepository.updateWallpaper(capture(wallpaperSlot)) } just runs

		// Act
		updateWallpaperIsFavouriteUseCase.execute(wallpaper)

		// Assert
		val updatedWallpaper = wallpaperSlot.captured
		assertThat(updatedWallpaper.isFavourite).isTrue()
	}

}