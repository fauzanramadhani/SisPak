package com.ndc.sispak.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ndc.sispak.ui.feature.home.HomeScreen
import com.ndc.sispak.ui.feature.splash.SplashScreen

@Composable
fun SetupNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    onStatusBarChange: (Boolean) -> Unit
) {
    val view = LocalView.current

    NavHost(
        navController = navHostController,
        startDestination = NavGraph.HomeScreen.route,
        route = NavGraph.ROOT.route
    ) {
        composable(route = NavGraph.SplashScreen.route) {
            SplashScreen(
                navHostController = navHostController
            )
        }
        composable(route = NavGraph.HomeScreen.route) {
            onStatusBarChange(false)
            HomeScreen(
                navHostController = navHostController
            )
        }
    }
}