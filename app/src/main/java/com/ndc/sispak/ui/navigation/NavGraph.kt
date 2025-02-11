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

    @Serializable
    data object CreateSystemScreen : NavGraph

    @Serializable
    data class ForwardChainingScreen(
        val screen: Int = 0,
        val systemId: Int = 0
    ) : NavGraph

}