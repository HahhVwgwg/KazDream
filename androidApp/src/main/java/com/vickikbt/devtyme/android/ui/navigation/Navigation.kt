package com.vickikbt.devtyme.android.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vickikbt.devtyme.android.ui.screens.home_screen.HomeScreen
import io.github.aakira.napier.Napier

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun Navigation(navController: NavHostController, isLoggedIn: Boolean) {

    Napier.e("Is user logged in: $isLoggedIn")

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) NavigationItem.Home.route else NavigationItem.Login.route
    ) {
        composable(route = NavigationItem.Home.route) {
            HomeScreen(navController = navController)
        }
    }
}
