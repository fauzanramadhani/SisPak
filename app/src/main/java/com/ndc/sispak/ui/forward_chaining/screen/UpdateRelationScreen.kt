package com.ndc.sispak.ui.forward_chaining.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ndc.sispak.R
import com.ndc.sispak.ui.component.card.BaseCard
import com.ndc.sispak.ui.forward_chaining.ForwardChainingAction
import com.ndc.sispak.ui.forward_chaining.ForwardChainingState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateRelationScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    state: ForwardChainingState,
    action: (ForwardChainingAction) -> Unit,
) {
    val ctx = LocalContext.current
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme
    val focus = LocalFocusManager.current

    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = state.loadingSwipeDiseases,
        onRefresh = {
            action(ForwardChainingAction.OnGetDisease)
        },
        state = pullToRefreshState,
        indicator = {
            Indicator(
                modifier = modifier
                    .padding(top = paddingValues.calculateTopPadding())
                    .align(Alignment.TopCenter),
                isRefreshing = state.loadingSwipeDiseases,
                state = pullToRefreshState,
                containerColor = color.primaryContainer,
                color = color.primary
            )
        }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(
                horizontal = 12.dp, vertical = 24.dp
            ),
            modifier = modifier
                .padding(paddingValues)
        ) {
            item {
                Column(
                    modifier = modifier.padding(
                        bottom = 12.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.what_is,
                            stringResource(id = R.string.symptom_with_disease)
                        ),
                        style = typography.titleSmall,
                        color = color.onBackground
                    )
                    BaseCard {
                        Text(
                            text = stringResource(id = R.string.symptom_description),
                            style = typography.bodySmall,
                            fontWeight = FontWeight.Normal,
                            color = color.onPrimaryContainer
                        )
                    }
                }
            }
            item {
                Text(
                    text = "Masukan Relasi Gejala dan Penyakit",
                    style = typography.titleSmall,
                    color = color.onBackground,
                    modifier = modifier
                        .padding(top = 12.dp)
                )
            }
            itemsIndexed(state.symptomWithDisease) { index, item ->

            }
        }
    }
}