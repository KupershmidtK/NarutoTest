package com.example.narutotest.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.narutotest.data.dao.NarutoCharEntity
import com.example.narutotest.data.local.NarutoDatabase
import com.example.narutotest.data.network.NarutoApiService
import com.example.narutotest.data.network.NarutoCharRemoteMediator
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNarutoDatabase(@ApplicationContext context: Context): NarutoDatabase {
        return Room.databaseBuilder(
            context,
            NarutoDatabase::class.java,
            "naruto.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesNarutoApiService(): NarutoApiService {
        val json: Json = Json {
            ignoreUnknownKeys = true
            allowStructuredMapKeys = true
        }

        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(NarutoApiService.BASE_URL)
            .build()
            .create()
    }


    @Provides
    @Singleton
    @OptIn(ExperimentalPagingApi::class)
    fun provideNarutoCharPager(narutoDb: NarutoDatabase, narutoApi: NarutoApiService): Pager<Int, NarutoCharEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = NarutoCharRemoteMediator(
                narutoDb = narutoDb,
                narutoApi = narutoApi
            ),
            pagingSourceFactory = {
                narutoDb.dao.pagingCharsSource()
            }
        )
    }
}