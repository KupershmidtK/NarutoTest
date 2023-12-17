package com.example.narutotest.ui.characters

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.narutotest.R
import com.example.narutotest.data.model.NarutoChar
import com.example.narutotest.ui.components.NarutoTopBar
import com.example.narutotest.ui.viewmodel.AppViewModelProvider
import com.example.narutotest.ui.viewmodel.CharDetailViewModel
import com.example.narutotest.ui.viewmodel.CharUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharDetailsScreen(
    navBack: () -> Unit,
    viewModel: CharDetailViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val uiState by viewModel.uiState.collectAsState()
    val title = if(uiState is CharUiState.Success) {
        (uiState as CharUiState.Success).character.name
    } else {
        stringResource(id = R.string.char_title)
    }

    Scaffold(
        topBar = {
            NarutoTopBar(
                title = title,
                navBack = navBack,
            )
        }
    ) { padding ->
        when (uiState) {
            CharUiState.Error -> ErrorToast()
            CharUiState.Loading -> LoadingScreen()
            is CharUiState.Success -> CharDetailCard(
                item = (uiState as CharUiState.Success).character,
                paddingValues = padding
            )
        }
    }

}

@Composable
fun CharDetailCard(
    item: NarutoChar,
    paddingValues: PaddingValues
) {
    val config = LocalConfiguration.current
    val orientationMode by remember { mutableStateOf(config.orientation) }

    Surface(
        modifier = Modifier.padding(paddingValues)
    ) {
        if (orientationMode == Configuration.ORIENTATION_PORTRAIT) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                NarutoImageCard(
                    imageList = item.images,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .border(12.dp, MaterialTheme.colorScheme.onSurface)
                )
                Spacer(Modifier.height(20.dp))
                CharPropertyList(item = item)
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
            ) {
                NarutoImageCard(
                    imageList = item.images,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(400.dp)
                        .border(12.dp, MaterialTheme.colorScheme.onSurface)
                )
                Spacer(Modifier.width(20.dp))
                CharPropertyList(item = item)
            }
        }
    }
}

@Composable
fun CharPropertyList(
    item: NarutoChar,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    )
    {
        if (!item.jutsu.isNullOrEmpty()) {
            Text(
                text = "Jutsu",
                style = MaterialTheme.typography.labelMedium
            )
            for (jutsu in item.jutsu) {
                Text(
                    text = "- $jutsu",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1
                )
            }
        }

        Spacer(Modifier.height(16.dp))
        if (!item.natureType.isNullOrEmpty()) {
            Text(
                text = "Nature Type",
                style = MaterialTheme.typography.labelMedium
            )
            for (natureType in item.natureType) {
                Text(
                    text = "- $natureType",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1)
            }
        }

        Spacer(Modifier.height(16.dp))
        if (!item.uniqueTraits.isNullOrEmpty()) {
            Text(
                text = "Unique Traits",
                style = MaterialTheme.typography.labelMedium
            )
            for (uniqueTrait in item.uniqueTraits) {
                Text(
                    text = "- $uniqueTrait",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1)
            }
        }
    }
}