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
class ClanViewModel @Inject constructor(
    private val narutoRepository: NarutoRepository
): ViewModel() {

    val uiState: StateFlow<VillageClanUiState> =
        narutoRepository.getAllClans()
            .map { clanListDto ->
                if (clanListDto != null) {
                    val clanList = clanListDto.map { it.toNarutoVillageClan() }
                    VillageClanUiState.Success(clanList)
                } else {
                    VillageClanUiState.Error
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = VillageClanUiState.Loading
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}