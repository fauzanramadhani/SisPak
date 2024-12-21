package com.ndc.sispak.ui.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ndc.sispak.R

@Composable
fun ErrorItem(
    modifier: Modifier = Modifier,
    message: String
) {
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.error_image_illustration),
            contentDescription = stringResource(id = R.string.cd_illustration_empty_image),
            modifier = Modifier
                .width(238.dp)
                .height(238.dp)
        )
        Text(
            text = message,
            style = typography.bodySmall,
            color = color.secondary,
        )
    }
}