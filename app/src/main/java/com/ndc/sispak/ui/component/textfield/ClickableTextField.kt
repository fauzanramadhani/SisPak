package com.ndc.sispak.ui.component.textfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ClickableTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: String = "",
    textFieldState: TextFieldState = TextFieldState.Empty,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val color = MaterialTheme.colorScheme
    val error = textFieldState is TextFieldState.Error
    val typography = MaterialTheme.typography

    BaseOutlinedTextField(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick, enabled = enabled),
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        enabled = false,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = color.primary,
            unfocusedBorderColor = color.outline,
            focusedContainerColor = color.background,
            unfocusedContainerColor = color.background,
            errorContainerColor = color.background,
            focusedTextColor = color.onBackground,
            unfocusedTextColor = color.onBackground,
            errorBorderColor = color.error,
            cursorColor = color.primary,
            errorCursorColor = color.primary,
            disabledBorderColor = color.outline,
            disabledContainerColor = color.background,
            disabledTextColor = color.onBackground,
        ),
        isError = error,
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
    )

}