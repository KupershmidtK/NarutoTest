package com.example.narutotest.data.dto

import com.example.narutotest.data.dao.NarutoCharEntity
import kotlinx.serialization.Serializable

@Serializable
data class NarutoCharDto(
    val id:	Int,
    val name:	String,
    val images:	List<String>? = null,
    //val debut: Debut? = null,
    //val personal: Personal? = null,
    //val family: Family? = null,
    val jutsu: List<String>? = null,
    val natureType: List<String>? = null,
    val uniqueTraits: List<String>? = null,
    //val rank: Rank? = null,
    //val voiceActors: Voice? = null,
)

@Serializable
data class NarutoCharsDto(
    val characters: List<NarutoCharDto>,
    val currentPage: Int,
    val pageSize: Int,
    val totalCharacters: Int,
)
