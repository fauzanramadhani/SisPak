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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.ndc.sispak.R
import com.ndc.sispak.common.Either
import com.ndc.sispak.common.MakeToast
import com.ndc.sispak.ui.component.app_bar.BottomSecondaryAppBar
import com.ndc.sispak.ui.component.app_bar.TopSecondaryAppBar
import com.ndc.sispak.ui.feature.create_system.screen.CreateName
import com.ndc.sispak.ui.feature.create_system.screen.DetailSystemScreen
import com.ndc.sispak.ui.feature.create_system.screen.SelectSystemScreen
import com.ndc.sispak.ui.navigation.NavGraph
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
            when (it) {
                is CreateSystemEffect.OnShowToast -> MakeToast(ctx).short(it.message)
                is CreateSystemEffect.OnSave -> when (it.methodId) {
                    1 -> navHostController.navigate(NavGraph.ForwardChainingScreen(systemId = it.systemId))
                }
            }
        }
    }

    BackHandler {
        when (state.screen) {
            0 -> {
                navHostController.navigateUp()
            }
            else -> action(CreateSystemAction.OnChangeScreen(state.screen - 1))
        }
    }

    Scaffold(
        topBar = {
            TopSecondaryAppBar(
                enabled = state.navigateButton,
                onBackPressed = {
                    when (state.screen) {
                        0 -> {
                            navHostController.navigateUp()
                        }
                        else -> action(CreateSystemAction.OnChangeScreen(state.screen - 1))
                    }
                }
            )
        },
        bottomBar = {
            if (state.bottomAppBarVisible) {
                BottomSecondaryAppBar(
                    loading = state.loadingSaveName,
                    title = when (state.screen) {
                        2 -> stringResource(id = R.string.save)
                        else -> stringResource(id = R.string.next)
                    },
                    onNextPressed = {
                        when (state.screen) {
                            1 -> action(CreateSystemAction.OnChangeScreen(2))
                            2 -> action(CreateSystemAction.OnCreateNameSave)
                        }
                    },
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
            2 -> CreateName(
                modifier = modifier,
                paddingValues = paddingValues,
                state = state,
                action = action
            )
        }
    }
}