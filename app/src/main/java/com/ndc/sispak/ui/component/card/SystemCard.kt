package com.ndc.sispak.ui.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ndc.sispak.R

@Composable
fun SystemCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onClick: () -> Unit = {},
) {
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme

    BaseCard(
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = title,
                    style = typography.titleSmall,
                    color = color.onPrimaryContainer,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.padding(bottom = 4.dp))
                Text(
                    text = description,
                    style = typography.labelMedium,
                    fontWeight = FontWeight.Normal,
                    color = color.onPrimaryContainer,
                    maxLines = 4
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right_secondary),
                contentDescription = stringResource(id = R.string.cd_ic_arrow_right),
                tint = color.onPrimaryContainer,
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}