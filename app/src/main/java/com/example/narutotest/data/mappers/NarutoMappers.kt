package com.example.narutotest.data.mappers

import com.example.narutotest.data.dao.NarutoCharEntity
import com.example.narutotest.data.dto.NarutoCharDto
import com.example.narutotest.data.dto.NarutoClanDto
import com.example.narutotest.data.dto.NarutoVillageDto
import com.example.narutotest.data.model.NarutoChar
import com.example.narutotest.data.model.NarutoVillageClan

fun NarutoCharDto.toCharEntity() = NarutoCharEntity(
    id = id,
    name = name,
    images = images?.joinToString(";") ?: "",
    jutsu = jutsu?.joinToString(";") ?: "",
    natureType = natureType?.joinToString(";") ?: "",
    uniqueTraits = uniqueTraits?.joinToString(";") ?: ""
)

fun NarutoCharEntity.toNarutoChar() = NarutoChar(
    id = id,
    name = name,
    images = images.split(";"),
    jutsu = jutsu.split(";"),
    natureType = natureType.split(";"),
    uniqueTraits = uniqueTraits.split(";")
)

fun NarutoClanDto.toNarutoVillageClan() = NarutoVillageClan (
    id = id,
    name = name,
    images = images,
    characters = characters?.map { it.toCharEntity().toNarutoChar() }
)

fun NarutoVillageDto.toNarutoVillageClan() = NarutoVillageClan (
    id = id,
    name = name,
    images = images,
    characters = characters?.map { it.toCharEntity().toNarutoChar() }
)