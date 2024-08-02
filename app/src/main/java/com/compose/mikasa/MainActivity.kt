package com.compose.mikasa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.compose.mikasa.navigation.AppNavigationGraph
import com.compose.mikasa.ui.theme.MikasaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MikasaTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Color.White
                        )

                ) {
                    MikasaEntryPoint()
                }
            }
        }
    }
}

@Composable
fun MikasaEntryPoint() {
    AppNavigationGraph()
}