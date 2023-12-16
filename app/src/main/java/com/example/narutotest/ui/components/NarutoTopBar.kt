package com.example.narutotest.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.example.narutotest.ui.theme.Carrot

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NarutoTopBar(
    title: String,
    navBack: () -> Unit,
//    navToCharList: (() -> Unit)? = null,
//    navToVillageList: (() -> Unit)? = null,
//    navToClanList: (() -> Unit)? = null,
) {

    return CenterAlignedTopAppBar(
        title = { Text(title) },
//        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//            containerColor = Carrot,
//
//            ),
        navigationIcon = {
            IconButton(onClick = navBack) {
                Icon(
                    Icons.Default.ArrowBack, contentDescription = null
                )
            }
        },
/*        actions = {
            IconButton(
                onClick = navToCharList ?: {},
                enabled = navToCharList != null
            ) {
                Icon(
                    Icons.Default.Person, contentDescription = null
                )
            }

            IconButton(
                onClick = navToVillageList ?: {},
                enabled = navToVillageList != null
            ) {
                Icon(Icons.Default.Home, contentDescription = null)
            }

            IconButton(
                onClick = navToClanList ?: {},
                enabled = navToClanList != null
            ) {
                Icon(Icons.Default.AccountBox, contentDescription = null)
            }
        }*/
    )
}