package com.example.narutotest.data.network

import com.example.narutotest.data.dto.NarutoCharsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NarutoApiService {

    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int, @Query("limit") limit: Int): NarutoCharsDto
//    suspend fun getAllCharacters(): String

    companion object {
        const val BASE_URL = "https://narutodb.xyz/api/"
    }
}