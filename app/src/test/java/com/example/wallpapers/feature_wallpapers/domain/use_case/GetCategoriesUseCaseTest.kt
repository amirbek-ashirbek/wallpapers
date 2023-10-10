package com.example.wallpapers.feature_wallpapers.domain.use_case

import com.example.wallpapers.feature_wallpapers.domain.model.WallpaperCategory
import com.example.wallpapers.feature_wallpapers.domain.repository.WallpaperRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCategoriesUseCaseTest {

	private lateinit var wallpaperRepository: WallpaperRepository
	private lateinit var getCategoriesUseCase: GetCategoriesUseCase

	@Before
	fun setUp() {
		wallpaperRepository = mockk<WallpaperRepository>()
		getCategoriesUseCase = GetCategoriesUseCase(wallpaperRepository = wallpaperRepository)
	}

	@Test
	fun `execute returns a non-null flow when repository returns non-null categories`() {

		// Arrange
		val expectedCategories = listOf(
			WallpaperCategory("id", "title", "url")
		)
		every { wallpaperRepository.getAllWallpaperCategories() } returns flowOf(expectedCategories)

		// Act
		val flow = getCategoriesUseCase.execute()

		// Assert
		runBlocking {
			flow.collect { categories ->
				assertThat(categories).isNotNull()
			}
		}
	}

	@Test
	fun `execute returns an empty list when repository returns empty list`() {

		// Arrange
		val expectedCategories = listOf<WallpaperCategory>()
		every { wallpaperRepository.getAllWallpaperCategories() } returns flowOf(expectedCategories)

		// Act
		val flow = getCategoriesUseCase.execute()

		// Assert
		runBlocking {
			flow.collect { categories ->
				assertThat(categories).isEmpty()
			}
		}
	}

}