package com.example.narutotest.ui.clans

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.narutotest.data.model.NarutoVillageClan
import com.example.narutotest.ui.components.ListSearchString
import com.example.narutotest.ui.components.NarutoTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VillageClanScreen(
    title: Int,
    uiState: VillageClanUiState,
    uiSearchState: State<String>,
    searchStringChange: (String) -> Unit,

    navBack: () -> Unit,

) {

    Scaffold(
        topBar = { NarutoTopBar(
            title = stringResource(id = title),
            navBack = navBack ) },
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            when(uiState) {
                VillageClanUiState.Error -> Text(text = "Error")
                VillageClanUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is VillageClanUiState.Success -> VillageClanList(
                    filteredList = uiState.filteredList,
                    uiSearchState = uiSearchState,
                    searchValueChange = searchStringChange
                )
            }
        }
    }
}

@Composable
fun VillageClanList(
    filteredList: List<NarutoVillageClan>,
    uiSearchState: State<String>,
    searchValueChange: (String) -> Unit,
) {
    Column(
//        modifier = Modifier
//            .padding(4.dp)
    ) {
        ListSearchString(
            state = uiSearchState,
            onValueChange = searchValueChange)
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            for (item in filteredList) {
                Text(
                    text = item.name,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable { /* TODO go to card */ }
                )
            }
        }
    }
}