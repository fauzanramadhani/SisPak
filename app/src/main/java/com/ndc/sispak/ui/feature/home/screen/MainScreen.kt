package com.ndc.sispak.ui.feature.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ndc.sispak.R
import com.ndc.sispak.ui.component.app_bar.PrimaryAppBar
import com.ndc.sispak.ui.component.card.EmptyItemCard
import com.ndc.sispak.ui.component.card.MyExpertSystemCard
import com.ndc.sispak.ui.component.shimmer.shimmerBrush
import com.ndc.sispak.ui.feature.home.HomeAction
import com.ndc.sispak.ui.feature.home.HomeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    action: (HomeAction) -> Unit,
    paddingValues: PaddingValues,
) {
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme
    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = state.loadingSwipeGetAllSystem,
        onRefresh = {
            action(HomeAction.OnGetAllSystem)
        },
        state = pullToRefreshState,
        indicator = {
            Indicator(
                modifier = modifier
                    .padding(top = paddingValues.calculateTopPadding() + 86.dp)
                    .align(Alignment.TopCenter),
                isRefreshing = state.loadingSwipeGetAllSystem,
                state = pullToRefreshState,
                containerColor = color.primaryContainer,
                color = color.primary
            )
        }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp + paddingValues.calculateBottomPadding())
        ) {
            item {
                PrimaryAppBar(
                    modifier = modifier
                        .padding(12.dp)
                        .padding(bottom = 12.dp)
                )
                Spacer(modifier = modifier.padding(top = 12.dp))
            }
            when {
                state.loadingGetAllSystem -> items(3) {
                    Box(
                        modifier = modifier
                            .padding(horizontal = 12.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .fillMaxWidth()
                            .height(76.dp)
                            .background(shimmerBrush())
                    )
                }
                state.myExpertSystem.isNotEmpty() -> {
                    item {
                        Column(
                            modifier = modifier.padding(
                                horizontal = 12.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.my_expert_system),
                                style = typography.titleSmall,
                                color = color.onBackground
                            )
                            Text(
                                text = stringResource(id = R.string.my_expert_system_description),
                                style = typography.labelSmall,
                                fontWeight = FontWeight.Normal,
                                color = color.onBackground
                            )
                        }
                    }
                    items(state.myExpertSystem) { item ->
                        MyExpertSystemCard(
                            modifier = modifier
                                .padding(horizontal = 12.dp),
                            title = item.title,
                            description = item.description,
                            methodName = item.method.title
                        ) {

                        }
                    }
                }
                else -> item {
                    EmptyItemCard(
                        modifier = modifier
                            .padding(top = 48.dp),
                        message = stringResource(id = R.string.empty_my_system)
                    )
                }
            }
        }
    }
}