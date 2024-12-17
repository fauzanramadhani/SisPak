package com.ndc.sispak.ui.feature.auth

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.ndc.sispak.common.Either
import com.ndc.sispak.common.MakeToast
import com.ndc.sispak.ui.component.app_bar.BackStackAppBar
import com.ndc.sispak.ui.feature.auth.screen.LoginScreen
import com.ndc.sispak.ui.feature.auth.screen.PersonalizationScreen
import com.ndc.sispak.ui.feature.auth.screen.RegisterScreen
import com.ndc.sispak.ui.navigation.NavGraph
import kotlinx.coroutines.flow.Flow

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    stateFlow: Flow<AuthState>,
    effectFlow: Flow<Either<AuthEffect>>,
    action: (AuthAction) -> Unit
) {
    val ctx = LocalContext.current

    val state by stateFlow.collectAsState(initial = AuthState())
    val effect by effectFlow.collectAsState(initial = Either.left())
    val toast = MakeToast(ctx)

    LaunchedEffect(key1 = effect) {
        effect.onRight {
            when (it) {
                AuthEffect.OnNavigateToDashboard -> navHostController.navigate(NavGraph.HomeScreen) {
                    launchSingleTop = true
                }

                is AuthEffect.OnShowToast -> toast.short(it.message)
            }
        }
    }

    BackHandler {
        if (state.screen == 0) (ctx as Activity).finish()
    }

    Scaffold(
        topBar = {
            if (state.screen != 0)
                BackStackAppBar(
                    onBackPressed = {
                        when (state.screen) {
                            1 -> action(AuthAction.OnScreenChange(0))
                        }
                    }
                )
        }
    ) { paddingValues ->
        when (state.screen) {
            0 -> LoginScreen(
                modifier = modifier,
                paddingValues = paddingValues,
                state = state,
                action = action
            )

            1 -> RegisterScreen(
                modifier = modifier,
                paddingValues = paddingValues,
                state = state,
                action = action
            )

            2 -> PersonalizationScreen(
                modifier = modifier,
                paddingValues = paddingValues,
                state = state,
                action = action
            )
        }
    }
}