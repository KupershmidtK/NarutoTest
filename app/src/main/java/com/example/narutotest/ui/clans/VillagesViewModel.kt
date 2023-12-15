package com.example.narutotest.ui.clans

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.narutotest.data.NarutoRepository
import com.example.narutotest.data.mappers.toNarutoVillageClan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class VillagesViewModel @Inject constructor(
    private val narutoRepository: NarutoRepository
): ViewModel() {

    val uiState: StateFlow<VillageClansUiState> =
        narutoRepository.getAllVillages()
            .map { villagesListDto ->
                if (villagesListDto != null) {
                    val clanList = villagesListDto.map { it.toNarutoVillageClan() }
                    VillageClansUiState.Success(clanList)
                } else {
                    VillageClansUiState.Error
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = VillageClansUiState.Loading
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}