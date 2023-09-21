package com.example.wallpapers.feature_wallpapers.wallpapers.data.remote

import com.example.wallpapers.BuildConfig
import com.example.wallpapers.feature_wallpapers.wallpapers.data.remote.model.image.ImageResponse
import com.example.wallpapers.feature_wallpapers.wallpapers.data.remote.model.topics.TopicResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

	@Headers("Authorization: Client-ID ${BuildConfig.UNSPLASH_API_KEY}")
	@GET("photos")
	suspend fun getImages(
		@Query("page") page: Int,
		@Query("per_page") perPage: Int? = null
	): List<ImageResponse>

	@Headers("Authorization: Client-ID ${BuildConfig.UNSPLASH_API_KEY}")
	@GET("topics")
	suspend fun getTopics(
		@Query("page") page: Int,
		@Query("per_page") perPage: Int? = null
	): List<TopicResponse>

}