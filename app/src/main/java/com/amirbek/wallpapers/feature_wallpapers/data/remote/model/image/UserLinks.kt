package com.amirbek.wallpapers.feature_wallpapers.data.remote.model.image


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserLinks(
    @Json(name = "html")
    val html: String,
)