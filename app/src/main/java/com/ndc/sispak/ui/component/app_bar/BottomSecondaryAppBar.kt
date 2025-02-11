package com.ndc.sispak.ui.component.app_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ndc.sispak.R

@Composable
fun BottomSecondaryAppBar(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.next),
    loading: Boolean = false,
    onNextPressed: () -> Unit = {}
) {
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Box(
        modifier = modifier
            .navigationBarsPadding()
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF70D4FF), Color(0xFF0214D8).copy(0.64f)),
                    start = Offset(0f, 0f),
                    end = Offset(700f, 0f)
                )
            ),
    ) {
        if (loading) {
            CircularProgressIndicator(
                color = color.onPrimary,
                strokeWidth = 3.dp,
                modifier = Modifier
                    .padding(end = 24.dp)
                    .size(16.dp)
                    .align(Alignment.CenterEnd),
            )
        } else {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .fillMaxHeight()
                    .clickable(onClick = onNextPressed)
                    .padding(horizontal = 12.dp)
                    .align(Alignment.CenterEnd),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = color.onPrimary
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = stringResource(id = R.string.cd_ic_arrow_right),
                    tint = color.onPrimary,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    }
}