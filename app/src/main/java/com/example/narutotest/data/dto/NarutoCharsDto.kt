package com.example.narutotest.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NarutoCharsDto(
    val characters: List<NarutoCharDto>,
    val currentPage: Int,
    val pageSize: Int,
    val totalCharacters: Int,
)