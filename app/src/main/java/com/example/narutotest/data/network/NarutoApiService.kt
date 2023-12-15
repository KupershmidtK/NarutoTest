package com.example.narutotest.data.network

import com.example.narutotest.data.dto.NarutoCharDto
import com.example.narutotest.data.dto.NarutoCharsDto
import com.example.narutotest.data.dto.NarutoClansDto
import com.example.narutotest.data.dto.NarutoVillagesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NarutoApiService {

    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int, @Query("limit") limit: Int): NarutoCharsDto

    @GET("character/search")
    suspend fun getCharacterByName(@Query("name") name: String): NarutoCharDto?

    @GET("clan")
    suspend fun getAllClans(@Query("page") page: Int, @Query("limit") limit: Int): NarutoClansDto

    @GET("village")
    suspend fun getAllVillages(@Query("page") page: Int, @Query("limit") limit: Int): NarutoVillagesDto

    companion object {
        const val BASE_URL = "https://narutodb.xyz/api/"
    }
}