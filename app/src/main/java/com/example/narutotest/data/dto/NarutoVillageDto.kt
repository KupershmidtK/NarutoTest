package com.example.narutotest.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NarutoVillageDto(
    val id:	Int,
    val name:	String,
    val images:	List<String>? = null,
    val characters: List<NarutoCharDto>? = null
)

@Serializable
data class NarutoVillagesDto(
    val villages: List<NarutoVillageDto>,
    val currentPage: Int,
    val pageSize: Int,
    val totalVillages: Int,
)