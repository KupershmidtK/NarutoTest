package com.example.narutotest.ui.characters

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.narutotest.data.NarutoRepository
import com.example.narutotest.data.mappers.toCharEntity
import com.example.narutotest.data.mappers.toNarutoChar
import com.example.narutotest.data.model.NarutoChar
import com.example.narutotest.ui.navigation.CharactersDetailsScreenDest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed interface CharUiState {
    data class Success(val character: NarutoChar) : CharUiState
    object Error : CharUiState
    object Loading : CharUiState
}

@HiltViewModel
class CharDetailViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    val narutoRepository: NarutoRepository
): ViewModel() {
    private val name: String = checkNotNull(savedStateHandle[CharactersDetailsScreenDest.charNameArg])

    val uiState: StateFlow<CharUiState> =
        narutoRepository.getCharacterByName(name)
            .map {
                if (it != null) {
                    CharUiState.Success(it.toCharEntity().toNarutoChar())
                } else {
                    CharUiState.Error
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = CharUiState.Loading
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}