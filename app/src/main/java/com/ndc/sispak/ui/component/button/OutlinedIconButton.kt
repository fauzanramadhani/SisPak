package com.ndc.sispak.ui.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.graphics.Color

@Composable
fun OutlinedIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String = "",
    icon: @Composable () -> Unit = {},
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(
        containerColor = MaterialTheme.colorScheme.onPrimary,
        contentColor = MaterialTheme.colorScheme.primary,
        disabledContentColor = MaterialTheme.colorScheme.surfaceVariant
    ),
    borderColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit = {}
) {
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    OutlinedButton(
        modifier = modifier
            .height(48.dp),
        onClick = onClick,
        colors = colors,
        border = BorderStroke(
            width = 1.dp,
            color = if (enabled) borderColor else color.surfaceVariant
        ),
        shape = RoundedCornerShape(8.dp),
        enabled = enabled,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()
            Text(
                text = text,
                style = typography.labelLarge
            )
        }
    }
}