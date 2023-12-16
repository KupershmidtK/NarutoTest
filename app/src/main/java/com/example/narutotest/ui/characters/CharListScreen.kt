package com.example.narutotest.ui.characters

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.narutotest.data.model.NarutoItem
import com.example.narutotest.ui.components.NarutoTopBar
import com.example.narutotest.ui.theme.Carrot20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharListScreen(
    itemList: LazyPagingItems<NarutoItem>,
    title: String,
    navBack: () -> Unit,
    modifier: Modifier = Modifier,
    navToDetails: (String) -> Unit,
) {

//    val itemList: LazyPagingItems<NarutoChar> = viewModel.narutoCharsFlow.collectAsLazyPagingItems()

    val context = LocalContext.current
    LaunchedEffect(key1 = itemList.loadState) {
        if(itemList.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (itemList.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    val config = LocalConfiguration.current
    val orientationMode by remember { mutableStateOf(config.orientation) }
    Scaffold(
        topBar = {
            NarutoTopBar(
                title = title,
                navBack = navBack,
            )
         },
//        containerColor = Carrot20,

    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            CharSearchString(onSearch = navToDetails)
//            Spacer(Modifier.height(4.dp))

            Box(modifier = Modifier.fillMaxSize()) {
                if (itemList.loadState.refresh is LoadState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    if (orientationMode == Configuration.ORIENTATION_PORTRAIT) {
                        VerticalList(characters = itemList, navToDetails = navToDetails)
                    } else {
                        HorizontalList(characters = itemList, navToDetails = navToDetails)
                    }
                }
            }
        }
    }
}

@Composable 
fun VerticalList(
    characters: LazyPagingItems<NarutoItem>,
    navToDetails: (String) -> Unit,
    ) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(characters.itemCount) { index ->
            val character = characters[index]
            if (character != null) {
                CharListCard(
                    item = character,
                    navToDetails = navToDetails,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(horizontal = 16.dp)
                )
            }
        }
        item {
            if (characters.loadState.append is LoadState.Loading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun HorizontalList(
    characters: LazyPagingItems<NarutoItem>,
    navToDetails: (String) -> Unit,
    ) {
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(characters.itemCount) { index ->
            val character = characters[index]
            if (character != null) {
                CharListCard(
                    item = character,
                    navToDetails = navToDetails,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(300.dp)
                        .padding(vertical = 8.dp)
                )
            }
        }
        item {
            if (characters.loadState.append is LoadState.Loading) {
                CircularProgressIndicator()
            }
        }
    }
}

