package com.compose.mikasa.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.mikasa.screens.BannerScreen
import com.compose.mikasa.screens.HomeScreen


@Composable
fun AppNavigationGraph() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.BANNER_SCREEN) {
        composable(Routes.HOME_SCREEN) {
            HomeScreen(navController = navController)
        }
        composable(Routes.BANNER_SCREEN) {
            BannerScreen(navController = navController)
        }
    }
}