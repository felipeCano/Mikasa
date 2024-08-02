package com.compose.mikasa.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.mikasa.utils.ResourceState
import com.compose.mikasa.viewmodel.MiKasaViewModel

@Composable
fun HomeScreen(
    miKasaViewModel: MiKasaViewModel = hiltViewModel()
) {
    val charactersResponse = miKasaViewModel.characters.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        when (charactersResponse) {
            is ResourceState.Loading<*> -> {
                Log.d("Loading", "homeScreen")
                Log.d("Loading", "tengo info?")
            }
            is ResourceState.Success<*> -> {
                Log.d("Success", "homeScreen")
                Log.d("Success", "tengo info?")
            }
            is ResourceState.Error<*> -> {
                Log.d("Error", "homeScreen")
                Log.d("Error", "tengo info?")
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
