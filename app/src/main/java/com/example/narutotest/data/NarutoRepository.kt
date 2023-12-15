package com.example.narutotest.data

import com.example.narutotest.data.dto.NarutoCharDto
import com.example.narutotest.data.dto.NarutoClanDto
import com.example.narutotest.data.dto.NarutoVillageDto
import com.example.narutotest.data.network.NarutoApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class NarutoRepository (val narutoApi: NarutoApiService) {
    fun getCharacterByName(name: String): Flow<NarutoCharDto?> =
        flow {
            val char = try { narutoApi.getCharacterByName(name) }
            catch (e: IOException) { null }
            catch (e: HttpException) { null }
            emit(char)
        }

    fun getAllClans(): Flow<List<NarutoClanDto>?> =
        flow {
            val item = try { narutoApi.getAllClans(1, 60) }
            catch (e: IOException) { null }
            catch (e: HttpException) { null }
            emit(item?.clans)
        }

    fun getAllVillages(): Flow<List<NarutoVillageDto>?> =
        flow {
            val item = try { narutoApi.getAllVillages(1, 60) }
            catch (e: IOException) { null }
            catch (e: HttpException) { null }
            emit(item?.villages)
        }
}