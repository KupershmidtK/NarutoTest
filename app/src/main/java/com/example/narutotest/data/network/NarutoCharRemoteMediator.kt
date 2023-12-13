package com.example.narutotest.data.network

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.narutotest.data.dao.NarutoCharEntity
import com.example.narutotest.data.dto.toCharEntity
import com.example.narutotest.data.local.NarutoDatabase
import com.example.narutotest.data.model.NarutoChar
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class NarutoCharRemoteMediator(
    private val narutoDb: NarutoDatabase,
    private val narutoApi: NarutoApiService
): RemoteMediator<Int, NarutoCharEntity>() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NarutoCharEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        ((lastItem.id + 1) / state.config.pageSize) + 1
                    }
                }
            }
            val narutoChars = narutoApi.getAllCharacters(
                page = loadKey,
                limit = state.config.pageSize
            )

            narutoDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    narutoDb.dao.clearAllChars()
                }

                val narutoCharEntity = narutoChars.characters.map { it.toCharEntity() }
                narutoDb.dao.upsertAllChars(narutoCharEntity)
            }

            MediatorResult.Success(
                endOfPaginationReached = narutoChars.characters.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}