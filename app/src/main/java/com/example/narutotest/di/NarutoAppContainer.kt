package com.example.narutotest.di

import android.content.Context
import com.example.narutotest.data.NarutoRepository
import com.example.narutotest.data.local.NarutoDatabase
import com.example.narutotest.data.network.NarutoApiService
import com.example.narutotest.notification.NarutoNotificationService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer
class NarutoAppContainer(private val context: Context): AppContainer {

    val json: Json = Json {
        ignoreUnknownKeys = true
        allowStructuredMapKeys = true
    }

    private val retrofit: Retrofit =  Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(NarutoApiService.BASE_URL)
        .build()


    private val narutoOnlineService: NarutoApiService by lazy {
        retrofit.create(NarutoApiService::class.java)
    }

    val narutoRepository: NarutoRepository by lazy {
        NarutoRepository(
            NarutoDatabase.getDatabase(context).dao,
            narutoOnlineService)
    }

    val narutoNotificationService: NarutoNotificationService by lazy {
        NarutoNotificationService(context)
    }

}