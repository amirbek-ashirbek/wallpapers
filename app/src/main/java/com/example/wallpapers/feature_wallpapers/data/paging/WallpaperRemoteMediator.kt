package com.example.wallpapers.feature_wallpapers.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.wallpapers.feature_wallpapers.data.local.database.WallpaperDatabase
import com.example.wallpapers.feature_wallpapers.data.local.model.WallpaperEntity
import com.example.wallpapers.feature_wallpapers.data.local.model.WallpaperRemoteKeys
import com.example.wallpapers.feature_wallpapers.data.remote.api.UnsplashApi
import com.example.wallpapers.feature_wallpapers.data.remote.model.image.ImageResponse.Companion.toWallpaperEntity
import kotlinx.coroutines.flow.firstOrNull
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class WallpaperRemoteMediator(
	private val categoryId: String,
	private val unsplashApi: UnsplashApi,
	private val wallpaperDatabase: WallpaperDatabase
) : RemoteMediator<Int, WallpaperEntity>() {

	private val wallpaperDao = wallpaperDatabase.wallpaperDao()
	private val wallpaperRemoteKeysDao = wallpaperDatabase.wallpaperRemoteKeysDao()

	override suspend fun load(
		loadType: LoadType,
		state: PagingState<Int, WallpaperEntity>
	): MediatorResult {
		return try {
			val currentPage = when (loadType) {
				LoadType.REFRESH -> {
					val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
					remoteKeys?.nextPage?.minus(1) ?: 1
				}
				LoadType.PREPEND -> {
					return MediatorResult.Success(endOfPaginationReached = true)
				}
				LoadType.APPEND -> {
					val remoteKeys = getRemoteKeyForLastItem(state)
					val nextPage = remoteKeys?.nextPage
						?: return MediatorResult.Success(
							endOfPaginationReached = remoteKeys != null
						)
					nextPage
				}
			}

			val response = unsplashApi.getImages(
				page = currentPage,
				categoryId = categoryId
			)
			val endOfPaginationReached = response.isEmpty()

			val prevPage = if (currentPage == 1) null else currentPage - 1
			val nextPage = if (endOfPaginationReached) null else currentPage + 1

			wallpaperDatabase.withTransaction {
				if (loadType == LoadType.REFRESH) {
					wallpaperDao.clearAll(categoryId = categoryId)
					wallpaperRemoteKeysDao.clearAll(categoryId = categoryId)
				}
				val keys = response.map { imageResponse ->
					WallpaperRemoteKeys(
						id = imageResponse.id,
						categoryId = categoryId,
						prevPage = prevPage,
						nextPage = nextPage
					)
				}
				wallpaperRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)

				val wallpapers = response.map { imageResponse ->
					val existingWallpaper = wallpaperDao.getWallpaperById(imageResponse.id).firstOrNull()
					val isFavourite = existingWallpaper?.isFavourite ?: false
					toWallpaperEntity(
						imageResponse = imageResponse,
						categoryId = categoryId,
						isFavourite = isFavourite
					)
				}
				wallpaperDao.upsertAll(wallpapers = wallpapers)
			}
			MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
		} catch (e: IOException) {
			MediatorResult.Error(e)
		} catch (e: HttpException) {
			MediatorResult.Error(e)
		}
	}

	private suspend fun getRemoteKeyClosestToCurrentPosition(
		state: PagingState<Int, WallpaperEntity>
	): WallpaperRemoteKeys? {
		return state.anchorPosition?.let { position ->
			state.closestItemToPosition(position)?.id?.let { id ->
				wallpaperRemoteKeysDao.getRemoteKeys(id = id, categoryId = categoryId)
			}
		}
	}

	private suspend fun getRemoteKeyForLastItem(
		state: PagingState<Int, WallpaperEntity>
	): WallpaperRemoteKeys? {

		val lastPage = state.pages.lastOrNull { it.data.isNotEmpty() }
		val lastItem = lastPage?.data?.lastOrNull()

		return lastItem?.let { wallpaperEntity ->
			wallpaperRemoteKeysDao.getRemoteKeys(id = wallpaperEntity.id, categoryId = wallpaperEntity.categoryId)
		}

	}

}