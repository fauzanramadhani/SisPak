package com.ndc.sispak.ui.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ndc.sispak.R

@Composable
fun SplashScreen(
    navHostController: NavHostController
) {
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFC2DEEA), Color(0xFF0251D8)),
                        start = Offset(0f, 0f),
                        end = Offset(0f, 2500f)
                    )
                )
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.cd_logo),
                modifier = Modifier
                    .width(162.dp)
                    .align(Alignment.Center)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 86.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
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
                    text = "Aplikasi Pembuat Sistem Pakar",
                    style = typography.bodyMedium,
                    color = color.onPrimary
                )
            }


        }
    }
}
