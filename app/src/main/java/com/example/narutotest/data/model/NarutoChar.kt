package com.example.narutotest.data.model

sealed class NarutoItem(
    open val id: Int,
    open val name: String,
    open val images: List<String>?
) {
    data class NarutoChar(
        override val id: Int,
        override val name: String,
        override val images: List<String>?,
        //val debut: Debut? = null,
        //val personal: Personal? = null,
        //val family: Family? = null,
        val jutsu: List<String>?,
        val natureType: List<String>?,
        val uniqueTraits: List<String>?,
        //val rank: Rank? = null,
        //val voiceActors: Voice? = null,
    ): NarutoItem(id, name, images)
}