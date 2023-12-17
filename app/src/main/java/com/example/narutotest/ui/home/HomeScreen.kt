package com.example.narutotest.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.narutotest.R
import com.example.narutotest.data.local.NarutoDao
import com.example.narutotest.data.local.NarutoDatabase
import com.example.narutotest.notification.NarutoNotificationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen (
    charactersOnClick: () -> Unit,
    clansOnClick: () -> Unit,
    villagesOnClick: () -> Unit,
) {
    // Orientation ------------------
    val config = LocalConfiguration.current
    val orientationMode by remember { mutableStateOf(config.orientation) }
    // Notification -------------------------------
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    //-----------------------------------------------

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (orientationMode == Configuration.ORIENTATION_PORTRAIT) {
            Column {
                HomeScreenImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(2f)
                        .clickable {
                            coroutineScope.launch {
                                withContext(Dispatchers.Default) {
                                    val narutoDao: NarutoDao =
                                        NarutoDatabase.getDatabase(context).dao

                                    val id = (1..1000).random()
                                    val text = narutoDao.getCharById(id).name
                                    NarutoNotificationService(context).showNotification(text)
                                }
                            }
                        }
                )
                HomeScreenButtons(
                    charactersOnClick = charactersOnClick,
                    clansOnClick = clansOnClick,
                    villagesOnClick = villagesOnClick,
                    modifier = Modifier
                        .weight(1f)
                )
            }
        } else {
            Row {
                HomeScreenImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(2f)
                        .clickable {
                            coroutineScope.launch {
                                withContext(Dispatchers.Default) {
                                    val narutoDao: NarutoDao =
                                        NarutoDatabase.getDatabase(context).dao

                                    val id = (1..1000).random()
                                    val text = narutoDao.getCharById(id).name
                                    NarutoNotificationService(context).showNotification(text)
                                }
                            }
                        }
                )
                HomeScreenButtons(
                    charactersOnClick = charactersOnClick,
                    clansOnClick = clansOnClick,
                    villagesOnClick = villagesOnClick,
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
    }
}
@Composable
fun HomeScreenImage(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.naruto),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            tint = MaterialTheme.colorScheme.surface
        )
    }
}

@Composable
fun HomeScreenButtons(
    charactersOnClick: () -> Unit,
    clansOnClick: () -> Unit,
    villagesOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val localModifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)

    Column(
        modifier = modifier
    ) {
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