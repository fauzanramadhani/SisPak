package com.ndc.sispak.ui.feature.create_system.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
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
import com.ndc.sispak.ui.component.card.EmptyItem
import com.ndc.sispak.ui.component.card.ErrorItem
import com.ndc.sispak.ui.component.card.SystemCard
import com.ndc.sispak.ui.component.shimmer.shimmerBrush
import com.ndc.sispak.ui.component.textfield.SearchTextField
import com.ndc.sispak.ui.feature.create_system.CreateSystemAction
import com.ndc.sispak.ui.feature.create_system.CreateSystemState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectSystemScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    state: CreateSystemState,
    action: (CreateSystemAction) -> Unit
) {
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme
    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = state.loading,
        onRefresh = {
            action(CreateSystemAction.OnReload)
        },
        state = pullToRefreshState,
        indicator = {
            Indicator(
                modifier = modifier
                    .padding(paddingValues.calculateTopPadding())
                    .align(Alignment.TopCenter),
                isRefreshing = state.loading,
                state = pullToRefreshState,
                containerColor = color.primaryContainer,
                color = color.primary
            )
        }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(
                bottom = 24.dp,
                top = paddingValues.calculateTopPadding() + 24.dp
            ),
            modifier = modifier
                .navigationBarsPadding()
        ) {
            item {
                Column(
                    modifier = modifier.padding(
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 12.dp
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.select_system),
                        style = typography.titleSmall,
                        color = color.onBackground
                    )
                    Spacer(modifier = modifier.padding(bottom = 4.dp))
                    Text(
                        text = stringResource(id = R.string.select_system_description),
                        style = typography.labelSmall,
                        fontWeight = FontWeight.Normal,
                        color = color.onBackground
                    )
                    Spacer(modifier = modifier.padding(bottom = 12.dp))
                    SearchTextField(modifier = Modifier.fillMaxWidth(),
                        value = state.searchValue,
                        onValueChange = {
                            action(CreateSystemAction.OnSearchValueChange(it))
                        }
                    )
                }
            }

            when {
                state.loading -> {
                    items(3) {
                        Box(
                            modifier = modifier
                                .padding(horizontal = 12.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .fillMaxWidth()
                                .height(76.dp)
                                .background(shimmerBrush())
                        )
                    }
                }

                state.errorLoadMethod.isNotEmpty() -> {
                    item {
                        ErrorItem(
                            message = state.errorLoadMethod,
                            modifier = modifier
                                .padding(horizontal = 12.dp)
                        )
                    }
                }

                state.methodVisible.isEmpty() -> item {
                    EmptyItem(message = stringResource(id = R.string.empty_method))
                }

                else -> items(state.methodVisible) { item ->
                    SystemCard(
                        modifier = Modifier
                            .padding(horizontal = 12.dp),
                        title = item.title,
                        description = item.description,
                    ) {

                    }
                }
            }
        }
    }
}