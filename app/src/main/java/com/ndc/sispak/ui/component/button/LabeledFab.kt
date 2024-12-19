package com.ndc.sispak.ui.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LabeledFab(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit = {},
    text: String = "",
    onClick: () -> Unit = {}
) {
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme

    FloatingActionButton(
        modifier = modifier,
        containerColor = color.primaryContainer,
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 12.dp, end = 24.dp)
        ) {
            icon()
            Text(
                text = text,
                style = typography.labelLarge,
                color = color.onPrimaryContainer,
            )
        }
    }
}