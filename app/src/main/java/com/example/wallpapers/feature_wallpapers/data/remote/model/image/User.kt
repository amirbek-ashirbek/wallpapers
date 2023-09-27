package com.example.wallpapers.feature_wallpapers.data.remote.model.image


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
	@Json(name = "links")
    val links: UserLinks,
	@Json(name = "username")
    val username: String
)