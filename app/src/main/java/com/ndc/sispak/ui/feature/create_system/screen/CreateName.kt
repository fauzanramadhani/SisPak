package com.ndc.sispak.ui.feature.create_system.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.ndc.sispak.R
import com.ndc.sispak.ui.component.textfield.PrimaryTextField
import com.ndc.sispak.ui.feature.create_system.CreateSystemAction
import com.ndc.sispak.ui.feature.create_system.CreateSystemState

@Composable
fun CreateName(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    state: CreateSystemState,
    action: (CreateSystemAction) -> Unit
) {

    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 12.dp, vertical = 24.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(id = R.string.title),
                style = typography.titleSmall,
                color = color.onBackground
            )
            PrimaryTextField(
                modifier = modifier
                    .fillMaxWidth(),
                placeholder = stringResource(id = R.string.et_title_system_ph),
                value = state.createNameTitleValue,
                onValueChange = {
                    action(CreateSystemAction.OnCreateNameTitleValueChange(it))
                },
                onClearValue = {
                    action(CreateSystemAction.OnCreateNameTitleValueChange(""))
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                textFieldState = state.createNameTitleState,
                maxLines = 2
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(id = R.string.summary),
                style = typography.titleSmall,
                color = color.onBackground
            )
            PrimaryTextField(
                modifier = modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                placeholder = stringResource(
                    id = R.string.et_summary_system_ph,
                    state.selectedMethod?.title ?: "Forward Chaining"
                ),
                value = state.createNameSummaryValue,
                onValueChange = {
                    action(CreateSystemAction.OnCreateNameSummaryValueChange(it))
                },
                onClearValue = {
                    action(CreateSystemAction.OnCreateNameSummaryValueChange(""))
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                maxLines = 4
            )
        }
    }
}