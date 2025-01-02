package com.ndc.sispak.ui.feature.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ndc.sispak.R
import com.ndc.sispak.common.Either
import com.ndc.sispak.common.MakeToast
import com.ndc.sispak.ui.component.app_bar.BottomNavigationBar
import com.ndc.sispak.ui.component.button.LabeledFab
import com.ndc.sispak.ui.feature.home.screen.ContributionScreen
import com.ndc.sispak.ui.feature.home.screen.MainScreen
import com.ndc.sispak.ui.feature.home.screen.SettingScreen
import com.ndc.sispak.ui.navigation.NavGraph
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    stateFlow: Flow<HomeState>,
    effectFlow: Flow<Either<HomeEffect>>,
    action: (HomeAction) -> Unit
) {
    val ctx = LocalContext.current
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme

    val state by stateFlow.collectAsState(initial = HomeState())
    val effect by effectFlow.collectAsState(initial = Either.left())

    LaunchedEffect(key1 = effect) {
        effect.onRight {
            when (it) {
                is HomeEffect.OnShowToast -> MakeToast(ctx).short(it.message)
                HomeEffect.OnLogout -> navHostController.navigate(NavGraph.AuthScreen(0)) {
                    launchSingleTop = true
                }
            }
        }
    }

    BackHandler {
        (ctx as Activity).finish()
    }

    Scaffold(
        modifier = modifier
            .navigationBarsPadding(),
        floatingActionButton = {
            when (state.screen) {
                0 -> LabeledFab(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add_square),
                            contentDescription = stringResource(id = R.string.cd_ic_add),
                            tint = color.onPrimaryContainer,
                            modifier = modifier
                                .size(24.dp)
                        )
                    },
                    text = stringResource(id = R.string.create),
                    onClick = {
                        navHostController.navigate(NavGraph.CreateSystemScreen) {
                            launchSingleTop = true
                        }
                    }
                )

                1 -> LabeledFab(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add_post),
                            contentDescription = stringResource(id = R.string.cd_ic_add),
                            tint = color.onPrimaryContainer,
                            modifier = modifier
                                .size(24.dp)
                        )
                    },
                    text = stringResource(id = R.string.fill_code),
                )
            }
        },
        bottomBar = {
            Surface(
                shadowElevation = 12.dp,
            ) {
                BottomAppBar(
                    containerColor = Color.White,
                ) {
                    BottomNavigationBar(
                        bottomNavigationItems = state.bottomNavigationItems,
                        selectedIndex = state.screen,
                        onSelectedIndexChange = {
                            action(HomeAction.OnSelectedScreenChange(it))
                        },
                    )
                }
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
            0 -> MainScreen(
                modifier = modifier,
                state = state,
                action = action,
                paddingValues = paddingValues
            )

            1 -> ContributionScreen(
                modifier = modifier,
                state = state,
                action = action,
                paddingValues = paddingValues
            )

            2 -> SettingScreen(
                modifier = modifier,
                state = state,
                action = action,
                paddingValues = paddingValues
            )
        }
    }
}