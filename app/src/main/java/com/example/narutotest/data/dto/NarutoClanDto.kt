package com.example.narutotest.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NarutoClanDto(
    val id:	Int,
    val name:	String,
    val images:	List<String>? = null,
    val characters: List<NarutoCharDto>? = null
)

@Serializable
data class NarutoClansDto(
    val clans: List<NarutoClanDto>,
    val currentPage: Int,
    val pageSize: Int,
    val totalClans: Int,
)
