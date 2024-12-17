package com.ndc.sispak.ui.feature.auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.ndc.sispak.R
import com.ndc.sispak.ui.component.button.PrimaryButton
import com.ndc.sispak.ui.component.textfield.PasswordTextField
import com.ndc.sispak.ui.component.textfield.PrimaryTextField
import com.ndc.sispak.ui.component.textfield.TextFieldState
import com.ndc.sispak.ui.feature.auth.AuthAction
import com.ndc.sispak.ui.feature.auth.AuthState

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    state: AuthState,
    action: (AuthAction) -> Unit
) {
    val ctx = LocalContext.current
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color.background)
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        if (!isSystemInDarkTheme()) {
            Image(
                painter = painterResource(id = R.drawable.register_background),
                contentDescription = "",
                modifier = Modifier
                    .height(203.dp)
            )
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 88.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(
                modifier = modifier
                    .padding(
                        start = 12.dp,
                    ),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.register_to),
                    style = typography.titleSmall.copy(fontWeight = FontWeight.Normal),
                    color = color.onBackground
                )
                Row {
                    Text(
                        text = "Sis",
                        style = typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = color.primary
                    )
                    Text(
                        text = "Pak",
                        style = typography.titleLarge,
                        color = color.primary
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.email),
                    style = typography.labelLarge,
                    color = color.onBackground
                )
                PrimaryTextField(
                    modifier = modifier
                        .fillMaxWidth(),
                    textFieldState = state.registerEmailState,
                    placeholder = stringResource(id = R.string.fill_your_email),
                    value = state.registerEmailValue,
                    onValueChange = {
                        action(AuthAction.OnRegisterEmailStateChange(TextFieldState.Empty))
                        action(AuthAction.OnRegisterEmailValueChange(it))
                    },
                    onClearValue = {
                        action(AuthAction.OnRegisterEmailValueChange(""))
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
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
                    text = stringResource(id = R.string.password),
                    style = typography.labelLarge,
                    color = color.onBackground
                )
                PasswordTextField(
                    modifier = modifier
                        .fillMaxWidth(),
                    textFieldState = state.registerPasswordState,
                    placeholder = stringResource(id = R.string.fill_your_password),
                    value = state.registerPasswordValue,
                    onValueChange = {
                        when {
                            it.isNotEmpty() && state.registerPasswordConfirmationValue.isNotEmpty()
                                    && it != state.registerPasswordConfirmationValue -> {
                                action(AuthAction.OnRegisterPasswordStateChange(TextFieldState.Empty))
                                action(
                                    AuthAction.OnRegisterPasswordConfirmationStateChange(
                                        TextFieldState.Error(ctx.getString(R.string.password_confirmation_is_incorrect))
                                    )
                                )
                            }

                            else -> {
                                action(AuthAction.OnRegisterPasswordStateChange(TextFieldState.Empty))
                                action(
                                    AuthAction.OnRegisterPasswordConfirmationStateChange(
                                        TextFieldState.Empty
                                    )
                                )
                            }
                        }
                        action(AuthAction.OnRegisterPasswordValueChange(it))
                    },
                    visible = state.registerPasswordVisible,
                    onVisibilityChange = {
                        action(AuthAction.OnRegisterPasswordVisibilityChange(it))
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
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
                    text = stringResource(id = R.string.confirm_password),
                    style = typography.labelLarge,
                    color = color.onBackground
                )
                PasswordTextField(
                    modifier = modifier
                        .fillMaxWidth(),
                    textFieldState = state.registerPasswordConfirmationState,
                    placeholder = stringResource(id = R.string.fill_your_password_again),
                    value = state.registerPasswordConfirmationValue,
                    onValueChange = {
                        when {
                            it.isNotEmpty() && state.registerPasswordValue != it -> {
                                action(
                                    AuthAction.OnRegisterPasswordConfirmationStateChange(
                                        TextFieldState.Error(ctx.getString(R.string.password_confirmation_is_incorrect))
                                    )
                                )
                            }

                            else -> {
                                action(
                                    AuthAction.OnRegisterPasswordConfirmationStateChange(
                                        TextFieldState.Empty
                                    )
                                )
                            }
                        }
                        action(AuthAction.OnRegisterPasswordConfirmationValueChange(it))
                    },
                    visible = state.registerPasswordConfirmationVisible,
                    onVisibilityChange = {
                        action(AuthAction.OnRegisterPasswordConfirmationVisibilityChange(it))
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus(true)
                        }
                    ),
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
                text = stringResource(id = R.string.register),
                enabled = !state.loadingState && state.registerEmailValue.isNotEmpty() && state.registerPasswordValue.isNotEmpty()
                        && state.registerPasswordConfirmationValue.isNotEmpty() && state.registerPasswordValue == state.registerPasswordConfirmationValue
                        && state.registerEmailState !is TextFieldState.Error && state.registerPasswordState !is TextFieldState.Error
                        && state.registerPasswordConfirmationState !is TextFieldState.Error,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                action(AuthAction.OnRegister)
            }
        }
    }
}