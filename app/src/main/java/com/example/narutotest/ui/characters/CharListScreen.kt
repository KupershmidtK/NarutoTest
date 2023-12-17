package com.example.narutotest.ui.characters

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.narutotest.data.model.NarutoChar
import com.example.narutotest.ui.components.ListSearchString
import com.example.narutotest.ui.components.NarutoTopBar
import com.example.narutotest.ui.viewmodel.AppViewModelProvider
import com.example.narutotest.ui.viewmodel.CharListUiState
import com.example.narutotest.ui.viewmodel.CharListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharListScreen(
    title: String,
    navBack: () -> Unit,
    navToDetails: (String) -> Unit,
    viewModel: CharListViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState
    val uiSearchState = viewModel.uiSearchState.collectAsState()

    Scaffold(
        topBar = {
            NarutoTopBar(
                title = title,
                navBack = navBack,
            )
         },

    ) { padding ->

        when(uiState) {
            CharListUiState.Error -> ErrorToast()
            CharListUiState.Loading -> LoadingScreen()
            is CharListUiState.Success -> {
                SuccessScreen(
                    filteredList = uiState.filteredList,
                    navToDetails = navToDetails,
                    uiSearchState = uiSearchState,
                    searchValueChange = viewModel::setSearchText,
                    padding = padding
                )
            }
        }
    }
}

@Composable
fun SuccessScreen(
    filteredList: List<NarutoChar>,
    uiSearchState: State<String>,
    padding: PaddingValues,
    searchValueChange: (String) -> Unit,
    navToDetails: (String) -> Unit,
) {
    val config = LocalConfiguration.current
    val orientationMode by remember { mutableStateOf(config.orientation) }

    Column(
        modifier = Modifier.padding(padding)
    ) {
        ListSearchString(
            state = uiSearchState,
            onValueChange = searchValueChange)

        Box(modifier = Modifier.fillMaxSize()) {
            if (orientationMode == Configuration.ORIENTATION_PORTRAIT) {
                PortraitCharList(
                    filteredList = filteredList,
                    navToDetails = navToDetails)
            } else {
                LandscapeCharList(
                    characters = filteredList,
                    navToDetails = navToDetails
                )
            }
        }
    }
}

@Composable 
fun PortraitCharList(
    filteredList: List<NarutoChar>,
    navToDetails: (String) -> Unit,
    ) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(filteredList) {
            CharListCard(
                item = it,
                navToDetails = navToDetails,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun LandscapeCharList(
    characters: List<NarutoChar>,
    navToDetails: (String) -> Unit,
    ) {
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(characters) {
            CharListCard(
                item = it,
                navToDetails = navToDetails,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(300.dp)
                    .padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun ErrorToast() {
    Toast.makeText(
        LocalContext.current,
        "Error: Can't load data from Internet",
        Toast.LENGTH_LONG
    ).show()
}