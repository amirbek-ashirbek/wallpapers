package com.amirbek.wallpapers.feature_wallpapers.data.remote.model.topics


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "id")
    val id: String,
    @Json(name = "links")
    val links: UserLinks,
    @Json(name = "name")
    val name: String,
    @Json(name = "portfolio_url")
    val portfolioUrl: String?,
    @Json(name = "username")
    val username: String
)