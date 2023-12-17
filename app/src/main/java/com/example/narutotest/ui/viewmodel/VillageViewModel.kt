package com.example.narutotest.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.narutotest.data.NarutoRepository
import com.example.narutotest.data.mappers.toNarutoVillageClan
import com.example.narutotest.ui.clans.VillageClanUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class VillageViewModel (
    private val narutoRepository: NarutoRepository
): ViewModel() {

    private val _uiSearchState = MutableStateFlow("")
    val uiSearchState: StateFlow<String> = _uiSearchState.asStateFlow()

    var uiState: VillageClanUiState by  mutableStateOf(VillageClanUiState.Loading)
        private set

    init {
        getAllVillages()
    }
    fun getAllVillages() {
        viewModelScope.launch {
            uiState = VillageClanUiState.Loading

            narutoRepository.getAllVillages()
                .map { villagesListDto ->
                    if (villagesListDto != null) {
                        val villageList = villagesListDto.map { it.toNarutoVillageClan() }
                        VillageClanUiState.Success(villageList, villageList)
                    } else {
                        VillageClanUiState.Error
                    }
                }
                .collect { uiState = it }
        }
    }

    fun setSearchText(text: String) {
        _uiSearchState.value = text
        if (uiState is VillageClanUiState.Success) {
            val successState = uiState as VillageClanUiState.Success
            val newList = if (text.length < 3) {
                successState.allItems
            } else {
                successState.allItems.filter { item ->
                    item.name.lowercase()
                        .contains(text.lowercase())
                }
            }
            uiState = successState.copy(filteredList = newList)
        }
    }
}