package com.ndc.sispak.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ndc.sispak.ui.feature.auth.AuthScreen
import com.ndc.sispak.ui.feature.auth.AuthViewModel
import com.ndc.sispak.ui.feature.home.HomeScreen
import com.ndc.sispak.ui.feature.home.HomeViewModel
import com.ndc.sispak.ui.feature.splash.SplashScreen
import com.ndc.sispak.ui.feature.splash.SplashViewModel

@Composable
fun SetupNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    onStatusBarChange: (Boolean) -> Unit
) {

    NavHost(
        navController = navHostController,
        startDestination = NavGraph.SplashScreen.route,
        route = NavGraph.ROOT.route
    ) {
        composable(route = NavGraph.SplashScreen.route) {
            val viewModel = hiltViewModel<SplashViewModel>()

            SplashScreen(
                modifier = modifier,
                navHostController = navHostController,
                stateFlow = viewModel.uiState,
                effectFlow = viewModel.onEffect,
                action = viewModel::invokeAction,
            )
        }
        composable(route = NavGraph.AuthScreen.route) {
            val viewModel = hiltViewModel<AuthViewModel>()

            AuthScreen(
                modifier = modifier,
                navHostController = navHostController,
                stateFlow = viewModel.uiState,
                effectFlow = viewModel.onEffect,
                action = viewModel::invokeAction
            )
        }
        composable(route = NavGraph.HomeScreen.route) {
            onStatusBarChange(false)
            val viewModel = hiltViewModel<HomeViewModel>()

            HomeScreen(
                modifier = modifier,
                navHostController = navHostController,
                stateFlow = viewModel.uiState,
                effectFlow = viewModel.onEffect,
                action = viewModel::invokeAction
            )
        }
    }
}