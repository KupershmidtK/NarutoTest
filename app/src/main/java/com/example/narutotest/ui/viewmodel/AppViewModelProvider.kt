package com.example.narutotest.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.narutotest.NarutoApplication

object AppViewModelProvider {

    val Factory: ViewModelProvider.Factory = viewModelFactory {

        initializer {
            CharListViewModel(
                narutoRepository = narutoApplication().container.narutoRepository
            )
        }

        initializer {
            CharDetailViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                narutoRepository = narutoApplication().container.narutoRepository
            )
        }

        initializer {
            ClanViewModel(
                narutoRepository = narutoApplication().container.narutoRepository
            )
        }

        initializer {
            VillageViewModel(
                narutoRepository = narutoApplication().container.narutoRepository
            )
        }
    }
}

fun CreationExtras.narutoApplication(): NarutoApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as NarutoApplication)