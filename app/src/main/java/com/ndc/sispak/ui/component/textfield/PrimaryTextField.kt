package com.ndc.sispak.ui.component.textfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.ndc.sispak.R

@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
    textFieldState: TextFieldState = TextFieldState.Empty,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLines: Int = 1,
    leadingIcon: @Composable (() -> Unit)? = null,
    onClearValue: () -> Unit = {},
    value: String = "",
    onValueChange: (String) -> Unit = {},
) {
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    val error = textFieldState is TextFieldState.Error

    BaseOutlinedTextField(
        modifier = modifier,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        isError = error,
        trailingIcon = if (value.isNotEmpty()) {
            {
                Icon(
                    painter = painterResource(
                        id = if (error) {
                            R.drawable.ic_info
                        } else {
                            R.drawable.ic_close
                        }

                    ),
                    contentDescription = "",
                    tint = if (error) {
                        color.error
                    } else {
                        color.onBackground
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(
                            enabled = !error,
                            onClick = onClearValue
                        )
                )
            }
        } else null,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        supportingText = when (textFieldState) {
            TextFieldState.Empty -> null
            is TextFieldState.Error -> {
                {
                    Text(
                        text = textFieldState.errorMessage,
                        style = typography.bodySmall,
                        color = color.error
                    )
                }
            }
        },
        maxLines = maxLines
    )
}