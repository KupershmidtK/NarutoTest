package com.example.narutotest.data.model

data class NarutoVillageClan(
    val id:	Int,
    val name:	String,
    val images:	List<String>? = null,
    val characters: List<NarutoChar>? = null
)
