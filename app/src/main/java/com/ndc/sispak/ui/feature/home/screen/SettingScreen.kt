package com.ndc.sispak.ui.feature.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ndc.sispak.R
import com.ndc.sispak.common.openLanguageSettings
import com.ndc.sispak.ui.component.app_bar.PrimaryAppBar
import com.ndc.sispak.ui.component.card.BaseCard
import com.ndc.sispak.ui.component.shimmer.shimmerBrush
import com.ndc.sispak.ui.feature.home.HomeAction
import com.ndc.sispak.ui.feature.home.HomeState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    action: (HomeAction) -> Unit,
    paddingValues: PaddingValues,
) {
    val ctx = LocalContext.current
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme
    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = state.loadingSwipe,
        onRefresh = {
            action(HomeAction.OnGetUserInfo)
        },
        state = pullToRefreshState,
        indicator = {
            Indicator(
                modifier = modifier
                    .padding(top = paddingValues.calculateTopPadding() + 86.dp)
                    .align(Alignment.TopCenter),
                isRefreshing = state.loadingSwipe,
                state = pullToRefreshState,
                containerColor = color.primaryContainer,
                color = color.primary
            )
        }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp + paddingValues.calculateBottomPadding()),
            modifier = modifier
                .fillMaxSize()
        ) {
            item {
                PrimaryAppBar(
                    modifier = modifier
                        .padding(12.dp)
                        .padding(bottom = 12.dp)
                )
            }
            item {
                if (state.loadingGetUserInfo || state.userInfo == null) {
                    Box(
                        modifier = modifier
                            .padding(horizontal = 12.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(shimmerBrush())
                    )
                } else {
                    BaseCard(
                        modifier = modifier
                            .padding(horizontal = 12.dp),
                        paddingValues = PaddingValues(
                            horizontal = 12.dp,
                            vertical = 8.dp
                        ),
                        onClick = {}
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_user_cicrle_fill),
                                contentDescription = stringResource(id = R.string.cd_ic_info),
                                modifier = modifier
                                    .size(48.dp)
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = state.userInfo.name,
                                    style = typography.labelSmall,
                                    color = color.onPrimaryContainer
                                )
                                Text(
                                    text = state.userInfo.email,
                                    style = typography.labelSmall,
                                    color = color.onPrimaryContainer,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                            Spacer(modifier = modifier.weight(1f))
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_right_secondary),
                                contentDescription = stringResource(id = R.string.cd_ic_arrow_right),
                                modifier = modifier
                                    .size(24.dp)
                            )
                        }
                    }
                }
            }
            item {
                BaseCard(
                    modifier = modifier
                        .padding(horizontal = 12.dp),
                    paddingValues = PaddingValues(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    ),
                    onClick = {}
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_info_circle_fill),
                            contentDescription = stringResource(id = R.string.cd_ic_info),
                            modifier = modifier
                                .size(24.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.terms_and_conditions),
                            style = typography.labelSmall,
                            color = color.onPrimaryContainer
                        )
                        Spacer(modifier = modifier.weight(1f))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_right_secondary),
                            contentDescription = stringResource(id = R.string.cd_ic_arrow_right),
                            modifier = modifier
                                .size(24.dp)
                        )
                    }
                }
            }
            item {
                BaseCard(
                    modifier = modifier
                        .padding(horizontal = 12.dp),
                    paddingValues = PaddingValues(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    ),
                    onClick = {}
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_send),
                            contentDescription = stringResource(id = R.string.cd_ic_send),
                            modifier = modifier
                                .size(24.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.send_feedback),
                            style = typography.labelSmall,
                            color = color.onPrimaryContainer
                        )
                        Spacer(modifier = modifier.weight(1f))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_right_secondary),
                            contentDescription = stringResource(id = R.string.cd_ic_arrow_right),
                            modifier = modifier
                                .size(24.dp)
                        )
                    }
                }
            }
            item {
                BaseCard(
                    modifier = modifier
                        .padding(horizontal = 12.dp),
                    paddingValues = PaddingValues(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    ),
                    onClick = {
                        openLanguageSettings(ctx)
                    }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_world),
                            contentDescription = stringResource(id = R.string.cd_ic_world),
                            modifier = modifier
                                .size(24.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.language),
                            style = typography.labelSmall,
                            color = color.onPrimaryContainer
                        )
                        Spacer(modifier = modifier.weight(1f))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_right_secondary),
                            contentDescription = stringResource(id = R.string.cd_ic_arrow_right),
                            modifier = modifier
                                .size(24.dp)
                        )
                    }
                }
            }
            item {
                BaseCard(
                    modifier = modifier
                        .padding(horizontal = 12.dp),
                    paddingValues = PaddingValues(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    ),
                    containerColor = color.errorContainer,
                    onClick = {
                        action(HomeAction.OnLogout)
                    }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_out_circle),
                            contentDescription = stringResource(id = R.string.cd_ic_out),
                            modifier = modifier
                                .size(24.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.out),
                            style = typography.labelSmall,
                            color = color.error
                        )
                        Spacer(modifier = modifier.weight(1f))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_right_secondary),
                            contentDescription = stringResource(id = R.string.cd_ic_arrow_right),
                            tint = color.error,
                            modifier = modifier
                                .size(24.dp)
                        )
                    }
                }
            }
        }
    }
}