package com.ndc.sispak.ui.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun BaseCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit,
) {
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme

    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(4.dp))
            .clickable(onClick = onClick)
            .background(color.primaryContainer)
            .fillMaxWidth()
            .padding(12.dp),
        content = content
    )
}