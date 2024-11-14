package com.ndc.sispak.ui.navigation

sealed class NavGraph(val route: String) {
    data object ROOT : NavGraph(route = "ROOT")
    data object SplashScreen : NavGraph(route = "SPLASH_SCREEN")
    data object HomeScreen : NavGraph(route = "HOME_SCREEN")
}