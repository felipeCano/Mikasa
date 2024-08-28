package com.compose.mikasa.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.compose.mikasa.components.CardLikeMovies
import com.compose.mikasa.components.EmptyStateComponent
import com.compose.mikasa.components.Loader
import com.compose.mikasa.utils.ResourceState
import com.compose.mikasa.utils.ResourceState.Loading
import com.compose.mikasa.viewmodel.MiKasaViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController,
    miKasaViewModel: MiKasaViewModel = hiltViewModel()
) {
    val charactersResponse by miKasaViewModel.characters.collectAsState()

    Scaffold(modifier = Modifier.background(Color.Transparent), topBar = {
        TopBar()
    }, content = { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.Transparent)
        ) {}
    })


    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        100
    }
    VerticalPager(
        state = pagerState,
        modifier = Modifier.wrapContentSize(),
        pageSize = PageSize.Fill,
        pageSpacing = 8.dp
    ) { page: Int ->

        when (charactersResponse) {
            is Loading -> {
                Log.d("Loading", "homeScreen")
                Loader()
            }

            is ResourceState.Success -> {
                val characters = (charactersResponse as ResourceState.Success).data
                Log.d("Success", "tengo info? ${characters.info.count} = ${characters.results}")
                if (characters.results.isNotEmpty()) {
                    CardLikeMovies(page, characters.results.get(page))
                } else {
                    EmptyStateComponent()
                }
            }

            is ResourceState.Error -> {
                val error = (charactersResponse as ResourceState.Error)
                Log.d("Error", "inside_Error $error")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(title = { Text(text = "Attack of titans") })
}

/*@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = NavController(context = Context))
}*/
