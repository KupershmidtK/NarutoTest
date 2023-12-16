package com.example.narutotest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.narutotest.ui.navigation.NarutoNavGraph
import com.example.narutotest.ui.theme.NarutoTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NarutoTestTheme(
                dynamicColor = false
            ) {
                NarutoNavGraph()
            }
        }
    }
}