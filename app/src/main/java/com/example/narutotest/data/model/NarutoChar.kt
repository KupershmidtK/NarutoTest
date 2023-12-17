package com.example.narutotest.data.model

data class NarutoChar(
        val id: Int,
        val name: String,
        val images: List<String>?,
        //val debut: Debut? = null,
        //val personal: Personal? = null,
        //val family: Family? = null,
        val jutsu: List<String>?,
        val natureType: List<String>?,
        val uniqueTraits: List<String>?,
        //val rank: Rank? = null,
        //val voiceActors: Voice? = null
)
