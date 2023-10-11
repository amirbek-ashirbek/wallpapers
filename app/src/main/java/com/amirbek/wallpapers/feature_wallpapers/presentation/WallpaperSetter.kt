package com.amirbek.wallpapers.feature_wallpapers.presentation

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.amirbek.wallpapers.util.Result
import javax.inject.Inject

class WallpaperSetter @Inject constructor(
	private val wallpaperManager: WallpaperManager,
	private val imageLoader: ImageLoader,
	private val context: Context
) {

	suspend fun setWallpaper(url: String, screen: WallpaperApplyScreen): Result<Unit> {

		return try {
			val wallpaperBitmap = getBitmap(url = url)
			val wallpaperFlag = when (screen) {
				WallpaperApplyScreen.HOME_SCREEN -> WallpaperManager.FLAG_SYSTEM
				WallpaperApplyScreen.LOCK_SCREEN -> WallpaperManager.FLAG_LOCK
				WallpaperApplyScreen.HOME_AND_LOCK_SCREENS -> WallpaperManager.FLAG_SYSTEM or WallpaperManager.FLAG_LOCK
			}
			wallpaperManager.setBitmap(wallpaperBitmap, null, true, wallpaperFlag)
			Result.Success(Unit)
		} catch (e: Exception) {
			Result.Failure(e.message)
		}
	}

	private suspend fun getBitmap(url: String): Bitmap {
		val request = ImageRequest.Builder(context = context)
			.data(url)
			.build()

		val result = (imageLoader.execute(request) as SuccessResult).drawable

		return (result as BitmapDrawable).bitmap
	}

}