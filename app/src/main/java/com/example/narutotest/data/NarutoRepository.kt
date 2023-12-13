package com.example.narutotest.data


import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.RemoteMediator
import com.example.narutotest.data.dto.NarutoCharDto
import com.example.narutotest.data.network.NarutoApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NarutoRepository (val narutoApi: NarutoApiService) {
    fun getCharacterByName(name: String): Flow<NarutoCharDto?> =
        flow {
            val char = try {
                narutoApi.getCharacterByName(name)
            } catch (e: IOException) {
                null
            } catch (e: HttpException) {
                null
            }
            emit(char)
        }
}