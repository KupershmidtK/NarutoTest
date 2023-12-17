package com.example.narutotest.data

import com.example.narutotest.data.dto.NarutoCharDto
import com.example.narutotest.data.dto.NarutoClanDto
import com.example.narutotest.data.dto.NarutoVillageDto
import com.example.narutotest.data.local.NarutoDao
import com.example.narutotest.data.mappers.toCharEntity
import com.example.narutotest.data.network.NarutoApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class NarutoRepository (
    private val narutoDb: NarutoDao,
    val narutoApi: NarutoApiService
) {
    fun getAllCharacters(): Flow<List<NarutoCharDto>?> =
        flow {
            val item = try { narutoApi.getAllCharacters(1, 1500)
            }
            catch (e: IOException) { null }
            catch (e: HttpException) { null }

            if (item != null) {
                val narutoCharEntity = item.characters.map { it.toCharEntity() }
                narutoDb.upsertAllChars(narutoCharEntity)
            }
            emit(item?.characters)
        }
    fun getCharacterByName(name: String): Flow<NarutoCharDto?> =
        flow {
            val char = try { narutoApi.getCharacterByName(name) }
            catch (e: IOException) { null }
            catch (e: HttpException) { null }
            emit(char)
        }

    fun getCharacterById(id: Int) = narutoDb.getCharById(id)

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