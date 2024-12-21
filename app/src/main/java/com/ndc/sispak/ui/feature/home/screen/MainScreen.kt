package com.ndc.sispak.ui.feature.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ndc.sispak.R
import com.ndc.sispak.ui.component.app_bar.PrimaryAppBar
import com.ndc.sispak.ui.component.card.MyExpertSystemCard
import com.ndc.sispak.ui.component.card.EmptyItem
import com.ndc.sispak.ui.feature.home.HomeAction
import com.ndc.sispak.ui.feature.home.HomeState

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    action: (HomeAction) -> Unit,
    paddingValues: PaddingValues,
) {
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 24.dp + paddingValues.calculateBottomPadding())
    ) {
        item {
            PrimaryAppBar(
                modifier = modifier
                    .padding(paddingValues.calculateTopPadding())
            )
        }
        if (state.myExpertSystem.isNotEmpty()) {
            item {
                Column(
                    modifier = modifier.padding(
                        start = 12.dp,
                        end = 12.dp,
                        top = 24.dp,
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
                    title = stringResource(id = item.title),
                    description = stringResource(id = item.description),
                    methodName = "Forward Chaining"
                ) {

                }
            }
        } else {
            item {
                EmptyItem(
                    modifier = modifier
                        .padding(top = 48.dp),
                    message = stringResource(id = R.string.empty_my_system)
                )
            }
        }
    }
}