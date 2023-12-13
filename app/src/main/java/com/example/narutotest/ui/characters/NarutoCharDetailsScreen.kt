package com.example.narutotest.ui.characters

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import com.example.narutotest.R
import com.example.narutotest.data.model.NarutoItem

@Composable
fun NarutoCharDetailsScreen(
    viewModel: NarutoCharDetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    when (uiState) {
        CharUiState.Error -> Text(text = "Something goes wrong")
        CharUiState.Loading -> CircularProgressIndicator()
        is CharUiState.Success -> CharSuccessCard(item = (uiState as CharUiState.Success).character)
    }
    

}

@Composable
fun CharSuccessCard(
    item: NarutoItem.NarutoChar,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { /* TODO */ },
        elevation = CardDefaults.cardElevation()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageUrl = if (item.images.isNullOrEmpty()) "" else item.images?.get(0)

            AsyncImage(
                model = imageUrl,
                contentDescription = item.name,
                error = painterResource(id = R.drawable.naruto),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(5f)
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .fillMaxWidth()
                    .border(20.dp, Color.White)

            )

            Text(
                text = item.name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                    .fillMaxWidth()
            )
        }
    }
}