package com.example.wallpapers.feature_wallpapers.data.remote

import com.example.wallpapers.BuildConfig
import com.example.wallpapers.feature_wallpapers.data.remote.model.image.ImageResponse
import com.example.wallpapers.feature_wallpapers.data.remote.model.topics.TopicResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {

	@Headers("Authorization: Client-ID ${BuildConfig.UNSPLASH_API_KEY}")
	@GET("topics/{id_or_slug}/photos")
	suspend fun getImages(
		@Path("id_or_slug") categoryId: String,
		@Query("page") page: Int,
		@Query("per_page") perPage: Int? = null,
		@Query("orientation") orientation: String = "portrait"
	): List<ImageResponse>

	@Headers("Authorization: Client-ID ${BuildConfig.UNSPLASH_API_KEY}")
	@GET("topics")
	suspend fun getTopics(
		@Query("page") page: Int,
		@Query("per_page") perPage: Int? = null
	): List<TopicResponse>

}