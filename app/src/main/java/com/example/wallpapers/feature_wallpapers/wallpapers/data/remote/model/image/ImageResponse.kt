package com.example.wallpapers.feature_wallpapers.wallpapers.data.remote.model.image


import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageResponse(
    @Json(name = "blur_hash")
    val blurHash: String?,
    @Json(name = "color")
    val color: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "description")
    val description: String?,
    @Json(name = "height")
    val height: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "likes")
    val likes: Int,
    @Json(name = "links")
    val links: Links,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "urls")
    val urls: Urls,
    @Json(name = "user")
    val user: User,
    @Json(name = "width")
    val width: Int
) {
    companion object {

        fun toWallpaperEntity(imageResponse: ImageResponse, categoryId: String): WallpaperEntity {
            return WallpaperEntity(
                id = imageResponse.id,
                categoryId = categoryId,
                url = imageResponse.urls.regular,
                downloadUrl = imageResponse.links.download
            )
        }
    }
}