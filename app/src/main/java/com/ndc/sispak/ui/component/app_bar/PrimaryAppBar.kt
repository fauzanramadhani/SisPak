package com.ndc.sispak.ui.component.app_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
fun PrimaryAppBar(
    modifier: Modifier = Modifier
) {
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme

    BaseAppBar(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier

        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(
                    id = R.string.cd_logo
                ),
                modifier = Modifier
                    .size(39.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row {
                    Text(
                        text = "Sis",
                        style = typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = color.onPrimary
                    )
                    Text(
                        text = "Pak",
                        style = typography.headlineSmall,
                        color = color.onPrimary
                    )
                }
                Text(
                    text = "By Neo Digital Creation",
                    style = typography.labelLarge,
                    color = color.onPrimary
                )
            }

        }
    }
}