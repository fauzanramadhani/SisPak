package com.ndc.sispak.ui.navigation

import kotlinx.serialization.Serializable

sealed interface NavGraph {
    @Serializable
    data object SplashScreen : NavGraph
    @Serializable
    data object HomeScreen : NavGraph
    @Serializable
    data class AuthScreen(
        val screen: Int = 0
    ) : NavGraph
}