package com.example.testdraw.ui.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.narutotest.R
import com.example.narutotest.notification.NarutoNotificationService

@Composable
fun HomeScreen (
    modifier: Modifier = Modifier,
    charactersOnClick: () -> Unit,
    clansOnClick: () -> Unit,
    villagesOnClick: () -> Unit,
) {
    val context = LocalContext.current

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            val localModifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
//                .border(2.dp, Color.Blue)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(5f),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.naruto),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    tint = MaterialTheme.colorScheme.surface
                )

            }

            Box(
                modifier = localModifier
                    .weight(1f)
                    .clickable {
                        NarutoNotificationService(context).showNotification("Test notification")
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Notification", fontSize = 16.sp)
            }

            Button(
                onClick = charactersOnClick,
                modifier = localModifier.weight(1f),
                shape = RectangleShape
            ) { Text( text = stringResource(id = R.string.char_title)) }
            Button(
                onClick = clansOnClick,
                modifier = localModifier.weight(1f),
                shape = RectangleShape
            ) { Text( text = stringResource(id = R.string.clans_title)) }
            Button(
                onClick = villagesOnClick,
                modifier = localModifier.weight(1f),
                shape = RectangleShape
            ) { Text( text = stringResource(id = R.string.villages_title)) }
        }
    }
}