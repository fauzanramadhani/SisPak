package com.ndc.sispak.ui.feature.create_system

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.ndc.sispak.R
import com.ndc.sispak.common.Either
import com.ndc.sispak.ui.component.app_bar.BottomSecondaryAppBar
import com.ndc.sispak.ui.component.app_bar.TopSecondaryAppBar
import com.ndc.sispak.ui.feature.create_system.screen.DetailSystemScreen
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

    LaunchedEffect(key1 = effect) {
        effect.onRight {
        }
    }

    BackHandler {
        when (state.screen) {
            0 -> {
                navHostController.navigateUp()
            }
        }
        action(CreateSystemAction.OnBackButtonPressed)
    }

    Scaffold(
        topBar = {
            TopSecondaryAppBar(
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
        },
        bottomBar = {
            if (state.bottomAppBarVisible) {
                BottomSecondaryAppBar(
                    enabled = true,
                    onNextPressed = {

                    }
                )
            }
        }
    ) { paddingValues ->
        Image(
            painter = painterResource(id = R.drawable.bg_white),
            contentScale = ContentScale.Crop,
            contentDescription = "",
            modifier = modifier
                .fillMaxSize()
        )
        when (state.screen) {
            0 -> SelectSystemScreen(
                modifier = modifier,
                paddingValues = paddingValues,
                state = state,
                action = action
            )

            1 -> DetailSystemScreen(
                modifier = modifier,
                paddingValues = paddingValues,
                state = state,
                action = action
            )
        }
    }
}