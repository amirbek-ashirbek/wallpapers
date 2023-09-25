package com.example.wallpapers.feature_wallpapers.wallpapers.presentation

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.Toast
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import javax.inject.Inject

class WallpaperSetter @Inject constructor(
	private val wallpaperManager: WallpaperManager,
	private val imageLoader: ImageLoader,
	private val context: Context
) {

	suspend fun setWallpaper(url: String, screen: WallpaperApplyScreen) {

		try {
			val wallpaperBitmap = getBitmap(url = url)
			val wallpaperFlag = when (screen) {
				WallpaperApplyScreen.HOME_SCREEN -> WallpaperManager.FLAG_SYSTEM
				WallpaperApplyScreen.LOCK_SCREEN -> WallpaperManager.FLAG_LOCK
				WallpaperApplyScreen.HOME_AND_LOCK_SCREENS -> WallpaperManager.FLAG_SYSTEM or WallpaperManager.FLAG_LOCK
			}
			wallpaperManager.setBitmap(wallpaperBitmap, null, true, wallpaperFlag)
			Toast.makeText(context, "Wallpaper set.", Toast.LENGTH_LONG).show()
		} catch (e: Exception) {
			Toast.makeText(context, "An error occurred.", Toast.LENGTH_LONG).show()
			Log.e("WallpaperSetter", e.message ?: "An error occurred while setting a wallpaper.")
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