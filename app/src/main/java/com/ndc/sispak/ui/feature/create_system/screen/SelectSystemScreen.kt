package com.ndc.sispak.ui.feature.create_system.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.ndc.sispak.ui.component.card.MyExpertSystemCard
import com.ndc.sispak.ui.component.empty.EmptyItem
import com.ndc.sispak.ui.component.textfield.SearchTextField
import com.ndc.sispak.ui.feature.create_system.CreateSystemAction
import com.ndc.sispak.ui.feature.create_system.CreateSystemState

@Composable
fun SelectSystemScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    state: CreateSystemState,
    action: (CreateSystemAction) -> Unit
) {
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(
            bottom = 24.dp,
            top = paddingValues.calculateTopPadding() + 0.dp
        )
    ) {
        item {
            Column(
                modifier = modifier.padding(horizontal = 12.dp, vertical = 24.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.my_expert_system),
                    style = typography.titleSmall,
                    color = color.onBackground
                )
                Spacer(modifier = Modifier.padding(bottom = 4.dp))
                Text(
                    text = stringResource(id = R.string.my_expert_system_description),
                    style = typography.labelSmall,
                    fontWeight = FontWeight.Normal,
                    color = color.onBackground
                )
                Spacer(modifier = Modifier.padding(bottom = 12.dp))
                SearchTextField(modifier = Modifier.fillMaxWidth(),
                    value = state.searchValue,
                    onValueChange = {
                        action(CreateSystemAction.OnSearchValueChange(it))
                    }
                )
            }
        }
        if (state.expertSystemVisible.isNotEmpty()) {
            items(state.expertSystemVisible) { item ->
                MyExpertSystemCard(
                    modifier = Modifier
                        .padding(horizontal = 12.dp),
                    title = stringResource(id = item.title),
                    description = stringResource(id = item.description),
                    methodName = "Forward Chaining"
                ) {

                }
            }
        } else {
            item {
                EmptyItem(message = "Ayo buat sistem pakar pertama anda!")
            }
        }
    }
}