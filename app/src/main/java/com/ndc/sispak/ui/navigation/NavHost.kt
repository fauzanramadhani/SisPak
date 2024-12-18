package com.ndc.sispak.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ndc.sispak.ui.feature.auth.AuthScreen
import com.ndc.sispak.ui.feature.auth.AuthViewModel
import com.ndc.sispak.ui.feature.create_system.CreateSystemScreen
import com.ndc.sispak.ui.feature.create_system.CreateSystemViewModel
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
        startDestination = NavGraph.SplashScreen,
    ) {
        composable<NavGraph.SplashScreen> {
            val viewModel = hiltViewModel<SplashViewModel>()

            SplashScreen(
                modifier = modifier,
                navHostController = navHostController,
                stateFlow = viewModel.uiState,
                effectFlow = viewModel.onEffect,
                action = viewModel::invokeAction,
            )
        }
        composable<NavGraph.AuthScreen>{
            val viewModel = hiltViewModel<AuthViewModel>()

            AuthScreen(
                modifier = modifier,
                navHostController = navHostController,
                stateFlow = viewModel.uiState,
                effectFlow = viewModel.onEffect,
                action = viewModel::invokeAction
            )
        }
        composable<NavGraph.HomeScreen> {
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
        composable<NavGraph.CreateSystemScreen> {
            onStatusBarChange(false)
            val viewModel = hiltViewModel<CreateSystemViewModel>()

            CreateSystemScreen(
                modifier = modifier,
                navHostController = navHostController,
                stateFlow = viewModel.uiState,
                effectFlow = viewModel.onEffect,
                action = viewModel::invokeAction
            )
        }
    }
}