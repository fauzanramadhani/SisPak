package com.ndc.sispak.ui.feature.auth.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ndc.sispak.R
import com.ndc.sispak.common.toDateString
import com.ndc.sispak.ui.component.button.PrimaryButton
import com.ndc.sispak.ui.component.sheet.DatePickerSheet
import com.ndc.sispak.ui.component.textfield.ClickableTextField
import com.ndc.sispak.ui.component.textfield.PrimaryTextField
import com.ndc.sispak.ui.component.textfield.TextFieldState
import com.ndc.sispak.ui.feature.auth.AuthAction
import com.ndc.sispak.ui.feature.auth.AuthState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalizationScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    state: AuthState,
    action: (AuthAction) -> Unit
) {
    val ctx = LocalContext.current
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val datePickerSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val datePickerState = rememberDatePickerState()

    if (datePickerSheetState.isVisible) {
        DatePickerSheet(
            sheetState = datePickerSheetState,
            datePickerState = datePickerState,
            onDismiss = {

            },
            onSelect = {
                scope.launch {
                    datePickerState.selectedDateMillis?.let {
                        action(AuthAction.OnPersonalizationDobValueChange(it))
                        action(AuthAction.OnPersonalizationNameStateChange(TextFieldState.Empty))
                    }
                    datePickerSheetState.hide()
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color.background)
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 88.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.name),
                    style = typography.labelLarge,
                    color = color.onBackground
                )
                PrimaryTextField(
                    modifier = modifier
                        .fillMaxWidth(),
                    textFieldState = state.registerEmailState,
                    placeholder = stringResource(id = R.string.fill_your_name),
                    value = state.personalizationNameValue,
                    onValueChange = {
                        action(AuthAction.OnPersonalizationNameStateChange(TextFieldState.Empty))
                        action(AuthAction.OnPersonalizationNameValueChange(it))
                    },
                    onClearValue = {
                        action(AuthAction.OnPersonalizationNameValueChange(""))
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text,
                        capitalization = KeyboardCapitalization.Words
                    ),
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.dob),
                    style = typography.labelLarge,
                    color = color.onBackground
                )
                ClickableTextField(
                    modifier = modifier.fillMaxWidth(),

                    placeholder = stringResource(id = R.string.fill_your_dob),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_calendar),
                            contentDescription = stringResource(id = R.string.cd_ic_calendar),
                        )
                    },
                    textFieldState = state.personalizationDobState,
                    value = state.personalizationDobValue.toDateString(),
                    onClick = {
                        scope.launch {
                            if (datePickerSheetState.isVisible) datePickerSheetState.hide()
                            else datePickerSheetState.show()
                        }
                    }
                )
            }
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center,
        ) {
            PrimaryButton(
                text = stringResource(id = R.string.save),
                enabled = !state.loadingState && state.personalizationNameValue.isNotEmpty() && state.personalizationDobValue != 0L
                        && state.personalizationNameState !is TextFieldState.Error && state.personalizationDobState !is TextFieldState.Error,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                action(AuthAction.OnSavePersonalization)
            }
        }
    }
}