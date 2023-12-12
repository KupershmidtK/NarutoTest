package com.example.narutotest.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.narutotest.data.dao.NarutoCharEntity
import com.example.narutotest.data.dao.toNarutoChar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class NarutoCharsViewModel @Inject constructor(
    val pager: Pager<Int, NarutoCharEntity>
)
    : ViewModel() {
        val narutoCharsFlow = pager
            .flow
            .map { pagingData ->
                pagingData.map { it.toNarutoChar() }
            }
            .cachedIn(viewModelScope)
}