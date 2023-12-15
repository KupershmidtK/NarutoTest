package com.example.narutotest.ui.clans

import com.example.narutotest.data.model.NarutoVillageClan

sealed interface VillageClansUiState {
    data class Success(val clans: List<NarutoVillageClan>) : VillageClansUiState
    object Error : VillageClansUiState
    object Loading : VillageClansUiState
}