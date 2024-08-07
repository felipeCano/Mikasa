package com.compose.mikasa.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.mikasa.components.EmptyStateComponent
import com.compose.mikasa.components.Loader
import com.compose.mikasa.components.MiKasaList
import com.compose.mikasa.components.MiKasaRowComponent
import com.compose.mikasa.utils.ResourceState
import com.compose.mikasa.utils.ResourceState.Loading
import com.compose.mikasa.viewmodel.MiKasaViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    miKasaViewModel: MiKasaViewModel = hiltViewModel()
) {
    val charactersResponse by miKasaViewModel.characters.collectAsState()

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        100
    }
    VerticalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
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
                    MiKasaRowComponent(page, characters.results.get(page))
                }else{
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


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
