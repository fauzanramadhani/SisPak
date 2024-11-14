package com.ndc.sispak.ui.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ndc.sispak.ui.component.app_bar.PrimaryAppBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme

    Scaffold() { paddingValues ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Column {
                    PrimaryAppBar(
                        modifier = modifier
                            .padding(paddingValues.calculateTopPadding())
                            .padding(vertical = 12.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 24.dp)
                    ) {
                        Text(
                            text = "Sistem Pakar Saya",
                            style = typography.titleSmall,
                            color = color.onBackground
                        )
                        Spacer(modifier = Modifier.padding(bottom = 4.dp))
                        Text(
                            text = "Sistem Pakar Saya",
                            style = typography.labelSmall,
                            fontWeight = FontWeight.Normal,
                            color = color.onBackground
                        )
                    }
                }

            }
        }
    }
}