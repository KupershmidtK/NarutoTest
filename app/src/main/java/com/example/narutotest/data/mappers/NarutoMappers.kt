package com.example.narutotest.data.mappers

import com.example.narutotest.data.dao.NarutoCharEntity
import com.example.narutotest.data.dto.NarutoCharDto
import com.example.narutotest.data.model.NarutoItem

fun NarutoCharDto.toCharEntity() = NarutoCharEntity(
    id = id,
    name = name,
    images = images?.joinToString(";") ?: "",
    jutsu = jutsu?.joinToString(";") ?: "",
    natureType = natureType?.joinToString(";") ?: "",
    uniqueTraits = uniqueTraits?.joinToString(";") ?: ""
)

fun NarutoCharEntity.toNarutoChar() = NarutoItem.NarutoChar(
    id = id,
    name = name,
    images = images.split(";"),
    jutsu = jutsu.split(";"),
    natureType = natureType.split(";"),
    uniqueTraits = uniqueTraits.split(";")
)