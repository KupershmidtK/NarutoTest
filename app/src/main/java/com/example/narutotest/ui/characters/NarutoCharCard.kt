package com.example.narutotest.ui.characters

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.narutotest.data.model.NarutoChar
import com.example.narutotest.ui.theme.NarutoTestTheme

@Composable
fun NarutoCharCard(
    item: NarutoChar,
    modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation()
    ) {
        Column (
            modifier = Modifier
                .width(400.dp)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageUrl = if (item.images.isNullOrEmpty()) "" else item.images[0]
            AsyncImage(
                model = imageUrl,
                contentDescription = item.name,
                modifier = Modifier
                    .height(300.dp)
                    .border(28.dp, Color.Black)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleLarge,
                )
        }
    }
}


@Preview
@Composable
fun NarutoCharCardPreview() {
    NarutoTestTheme {
        NarutoCharCard(
            item = NarutoChar(
                id = 17,
                name = "Ada",
                images = listOf("https://static.wikia.nocookie.net/naruto/images/f/f6/Ada_Infobox_Image.png"),
                jutsu = listOf("Parasitic Destruction Insect Technique (Kochū)"),
                natureType = listOf(
                    "Fire Release",
                    "Wind Release",
                    "Lightning Release",
                    "Earth Release",
                    "Water Release",
                    "Wood Release",
                    "Yin Release",
                    "Yang Release",
                    "Yin–Yang Release"
                ),
                uniqueTraits = listOf("Can absorb chakra")
            )
        )
    }
}