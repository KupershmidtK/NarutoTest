package com.example.narutotest.ui.clans

import com.example.narutotest.data.model.NarutoVillageClan

sealed interface VillageClanUiState {
    data class Success(
        val allItems: List<NarutoVillageClan>,
        val filteredList: List<NarutoVillageClan>) : VillageClanUiState
    object Error : VillageClanUiState
    object Loading : VillageClanUiState
}