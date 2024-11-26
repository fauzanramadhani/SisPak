package com.ndc.sispak.ui.feature.auth.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.ndc.sispak.R
import com.ndc.sispak.ui.component.button.OutlinedButton
import com.ndc.sispak.ui.component.button.OutlinedIconButton
import com.ndc.sispak.ui.component.button.PrimaryButton
import com.ndc.sispak.ui.component.textfield.PasswordTextField
import com.ndc.sispak.ui.component.textfield.PrimaryTextField
import com.ndc.sispak.ui.component.textfield.TextFieldState
import com.ndc.sispak.ui.feature.auth.AuthAction
import com.ndc.sispak.ui.feature.auth.AuthState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    state: AuthState,
    action: (AuthAction) -> Unit
) {
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    val focusManager = LocalFocusManager.current
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(), onResult = { result ->
            result.data?.let {
                action(AuthAction.OnHandleLoginWithGoogle(it))
            }
        }
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color.background)
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        if (!isSystemInDarkTheme()) {
            Image(
                painter = painterResource(id = R.drawable.login_background),
                contentDescription = "",
                modifier = Modifier
                    .height(203.dp)
            )
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Row(
                modifier = modifier
                    .padding(
                        start = 12.dp,
                    )
            ) {
                Text(
                    text = "Masuk ke ",
                    style = typography.titleLarge,
                    color = color.onBackground
                )
                Text(
                    text = "Sis",
                    style = typography.titleLarge,
                    color = color.primary
                )
                Text(
                    text = "Pak",
                    style = typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = color.primary
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = "Email",
                    style = typography.labelLarge,
                    color = color.onBackground
                )
                PrimaryTextField(
                    modifier = modifier
                        .fillMaxWidth(),
                    textFieldState = state.loginEmailState,
                    placeholder = "Masukan email kamu",
                    value = state.loginEmailValue,
                    onValueChange = {
                        action(AuthAction.OnLoginPasswordStateChange(TextFieldState.Empty))
                        action(AuthAction.OnLoginEmailValueChange(it))
                    },
                    onClearValue = {
                        action(AuthAction.OnLoginEmailValueChange(""))
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = "Password",
                    style = typography.labelLarge,
                    color = color.onBackground
                )
                PasswordTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textFieldState = state.loginPasswordState,
                    placeholder = "Masukan password kamu",
                    value = state.loginPasswordValue,
                    onValueChange = {
                        action(AuthAction.OnLoginPasswordValueChange(it))
                    },
                    visible = state.loginPasswordVisible,
                    onVisibilityChange = {
                        action(AuthAction.OnLoginPasswordStateChange(TextFieldState.Empty))
                        action(AuthAction.OnLoginPasswordVisibilityChange(it))
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
            Text(
                text = "Lupa password?",
                style = typography.labelLarge,
                color = color.primary,
                modifier = Modifier
                    .padding(start = 12.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            ) {
                OutlinedButton(
                    text = "Daftar",
                    modifier = Modifier
                        .weight(1f)
                ) {
                    action(AuthAction.OnScreenChange(1))
                }
                PrimaryButton(
                    text = "Masuk",
                    enabled = state.loginEmailValue.isNotEmpty() && state.loginEmailState !is TextFieldState.Error
                            && state.loginPasswordValue.isNotEmpty() && state.loginPasswordState !is TextFieldState.Error,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    action(AuthAction.OnLoginBasic)
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HorizontalDivider(
                    color = color.outline,
                    thickness = 0.5.dp,
                    modifier = Modifier
                        .weight(1f)
                )
                Text(
                    text = "ATAU",
                    style = typography.bodySmall,
                    color = color.onSurfaceVariant,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                )
                Divider(
                    color = color.outline,
                    thickness = 0.5.dp,
                    modifier = Modifier
                        .weight(1f)
                )
            }
            OutlinedIconButton(
                enabled = state.loginGoogleButtonEnabled,
                text = "Masuk dengan google",
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = ""
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
            ) {
                state.gso?.let {
                    googleSignInLauncher.launch(it)
                }

            }
        }
    }
}