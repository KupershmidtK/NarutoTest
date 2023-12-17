package com.example.narutotest.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.narutotest.data.NarutoRepository
import com.example.narutotest.data.mappers.toCharEntity
import com.example.narutotest.data.mappers.toNarutoChar
import com.example.narutotest.data.model.NarutoChar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

sealed interface CharListUiState {
    data class Success(val charList: List<NarutoChar>, val filteredList: List<NarutoChar>) :
        CharListUiState
    object Error : CharListUiState
    object Loading : CharListUiState
}

class CharListViewModel (
    private val narutoRepository: NarutoRepository
): ViewModel() {

    private val _uiSearchState = MutableStateFlow("")
    val uiSearchState: StateFlow<String> = _uiSearchState.asStateFlow()

    var uiState: CharListUiState by mutableStateOf(CharListUiState.Loading)
        private set

    init {
        getAllCharacters()
    }
    fun getAllCharacters()  {
        viewModelScope.launch {
            uiState = CharListUiState.Loading

            narutoRepository.getAllCharacters()
                .map { charListDto ->
                    if (charListDto != null) {
                        val charList = charListDto.map { it.toCharEntity().toNarutoChar() }
                        CharListUiState.Success(charList, charList)
                    } else {
                        CharListUiState.Error
                    }
                }
            .collect{ uiState = it }
        }
    }

    fun setSearchText(text: String) {
        _uiSearchState.value = text
        if (uiState is CharListUiState.Success) {
            val successState = uiState as CharListUiState.Success
            val newList = if (text.length < 3) {
                successState.charList
            } else {
                successState.charList.filter { item ->
                    item.name.lowercase()
                        .contains(text.lowercase())
                }
            }
            uiState = successState.copy(filteredList = newList)
        }
    }
}
