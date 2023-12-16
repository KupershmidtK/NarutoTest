package com.example.narutotest.ui.clans

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.narutotest.data.model.NarutoVillageClan
import com.example.narutotest.ui.components.NarutoTopBar
import com.example.narutotest.ui.theme.Carrot
import com.example.narutotest.ui.theme.Carrot20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VillageClanScreen(
    title: Int,
    navBack: () -> Unit,
    uiState: VillageClanUiState
) {

    Scaffold(
        topBar = { NarutoTopBar(
            title = stringResource(id = title),
            navBack = navBack ) },
        containerColor = Carrot20
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            when(uiState) {
                VillageClanUiState.Error -> Text(text = "Error")
                VillageClanUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is VillageClanUiState.Success -> VillageClanList(items = (uiState as VillageClanUiState.Success).clans)
            }
        }
    }
}

@Composable
fun VillageClanList(items: List<NarutoVillageClan>) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        for (item in items) {
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

/*
    LazyColumn(
         modifier = Modifier.padding(top = 2.dp),
//        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(clans) { clan ->
            VillageClanCard(clan)
        }
    }


@Composable
fun VillageClanCard(clan: NarutoVillageClan) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 4.dp),
//        colors = CardDefaults.cardColors(containerColor = Carrot),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = clan.name)
        }
    }
}

 */
