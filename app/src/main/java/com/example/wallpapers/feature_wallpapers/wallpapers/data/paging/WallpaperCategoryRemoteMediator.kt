package com.example.wallpapers.feature_wallpapers.wallpapers.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.WallpaperDatabase
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperCategoryEntity
import com.example.wallpapers.feature_wallpapers.wallpapers.data.local.model.WallpaperCategoryRemoteKeys
import com.example.wallpapers.feature_wallpapers.wallpapers.data.remote.UnsplashApi
import com.example.wallpapers.feature_wallpapers.wallpapers.data.remote.model.topics.TopicResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class WallpaperCategoryRemoteMediator @Inject constructor(
	private val unsplashApi: UnsplashApi,
	private val wallpaperDatabase: WallpaperDatabase
) : RemoteMediator<Int, WallpaperCategoryEntity>() {

	private val categoryDao = wallpaperDatabase.wallpaperCategoryDao()
	private val categoryRemoteKeysDao = wallpaperDatabase.wallpaperCategoryRemoteKeysDao()

	override suspend fun load(
		loadType: LoadType,
		state: PagingState<Int, WallpaperCategoryEntity>
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

			val response = unsplashApi.getTopics(page = currentPage)
			val endOfPaginationReached = response.isEmpty()

			val prevPage = if (currentPage == 1) null else currentPage - 1
			val nextPage = if (endOfPaginationReached) null else currentPage + 1

			wallpaperDatabase.withTransaction {
				if (loadType == LoadType.REFRESH) {
					categoryDao.clearAll()
					categoryRemoteKeysDao.clearAll()
				}
				val keys = response.map { imageResponse ->
					WallpaperCategoryRemoteKeys(
						id = imageResponse.id,
						prevPage = prevPage,
						nextPage = nextPage
					)
				}
				categoryRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)

				val categories = response.map { TopicResponse.toWallpaperCategoryEntity(it) }
				categoryDao.insertAll(categories = categories)
			}
			MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
		} catch (e: IOException) {
			MediatorResult.Error(e)
		} catch (e: HttpException) {
			MediatorResult.Error(e)
		}
	}

	private suspend fun getRemoteKeyClosestToCurrentPosition(
		state: PagingState<Int, WallpaperCategoryEntity>
	): WallpaperCategoryRemoteKeys? {
		return state.anchorPosition?.let { position ->
			state.closestItemToPosition(position)?.id?.let { id ->
				categoryRemoteKeysDao.getRemoteKeys(id = id)
			}
		}
	}

	private suspend fun getRemoteKeyForLastItem(
		state: PagingState<Int, WallpaperCategoryEntity>
	): WallpaperCategoryRemoteKeys? {

		val lastPage = state.pages.lastOrNull { it.data.isNotEmpty() }
		val lastItem = lastPage?.data?.lastOrNull()

		return lastItem?.let { categoryEntity ->
			categoryRemoteKeysDao.getRemoteKeys(id = categoryEntity.id)
		}

	}

}