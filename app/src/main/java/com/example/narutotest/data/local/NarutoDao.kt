package com.example.narutotest.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.narutotest.data.dao.NarutoCharEntity

@Dao
interface NarutoDao {
    @Upsert
    suspend fun upsertAllChars(chars: List<NarutoCharEntity>)

    @Query("SELECT * FROM narutocharentity")
    fun pagingCharsSource(): PagingSource<Int, NarutoCharEntity>

    @Query("DELETE FROM narutocharentity")
    suspend fun clearAllChars()

    @Query("SELECT * FROM narutocharentity WHERE id = :id")
    fun getCharById(id: Int): NarutoCharEntity
}