package com.example.wallpapers.feature_wallpapers.wallpapers.data.remote.model.topics


import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperCategoryEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopicResponse(
    @Json(name = "cover_photo")
    val coverPhoto: CoverPhoto,
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "starts_at")
    val startsAt: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "visibility")
    val visibility: String
) {

    companion object {
        fun toWallpaperCategoryEntity(topicResponse: TopicResponse): WallpaperCategoryEntity {
            return WallpaperCategoryEntity(
                id = topicResponse.id,
                title = topicResponse.title,
                coverPhotoUrl = topicResponse.coverPhoto.urls.regular
            )
        }
    }

}