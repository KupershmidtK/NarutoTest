package com.example.narutotest.data.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.narutotest.data.model.NarutoChar

@Entity
data class NarutoCharEntity(
    @PrimaryKey
    val id:	Int,
    val name: String,
    val images:	String,
    //val debut: Debut? = null,
    //val personal: Personal? = null,
    //val family: Family? = null,
    val jutsu: String,
    val natureType: String,
    val uniqueTraits: String,
    //val rank: Rank? = null,
    //val voiceActors: Voice? = null,
)
