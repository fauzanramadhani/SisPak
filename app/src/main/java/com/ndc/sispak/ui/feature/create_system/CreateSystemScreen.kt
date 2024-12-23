package com.ndc.sispak.ui.feature.create_system

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.ndc.sispak.common.Either
import com.ndc.sispak.ui.component.app_bar.BackStackAppBar
import com.ndc.sispak.ui.feature.create_system.screen.SelectSystemScreen
import kotlinx.coroutines.flow.Flow

@Composable
fun CreateSystemScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    stateFlow: Flow<CreateSystemState>,
    effectFlow: Flow<Either<CreateSystemEffect>>,
    action: (CreateSystemAction) -> Unit
) {
    val ctx = LocalContext.current
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme

    val state by stateFlow.collectAsState(initial = CreateSystemState())
    val effect by effectFlow.collectAsState(initial = Either.left())
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            BackStackAppBar(
                enabled = state.backButtonEnabled,
                onBackPressed = {
                    when (state.screen) {
                        0 -> {
                            navHostController.navigateUp()
                        }
                    }
                    action(CreateSystemAction.OnBackButtonPressed)
                }
            )
        }
    ) { paddingValues ->
        when (state.screen) {
            0 -> SelectSystemScreen(
                modifier = modifier,
                paddingValues = paddingValues,
                state = state,
                action = action
            )
        }
    }
}