package com.ndc.sispak.ui.feature.create_system.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.binayshaw7777.kotstep.model.StepDefaults
import com.binayshaw7777.kotstep.model.StepStyle
import com.binayshaw7777.kotstep.model.numberedVerticalWithLabel
import com.binayshaw7777.kotstep.ui.vertical.VerticalStepper
import com.ndc.sispak.ui.component.card.BaseCard
import com.ndc.sispak.ui.feature.create_system.CreateSystemAction
import com.ndc.sispak.ui.feature.create_system.CreateSystemState

@Composable
fun DetailSystemScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    state: CreateSystemState,
    action: (CreateSystemAction) -> Unit
) {
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 12.dp, vertical = 24.dp)
    ) {
        state.selectedMethod?.let { method ->
            Text(
                text = "Apa itu ${method.title}",
                style = typography.titleSmall,
                color = color.onBackground
            )
            BaseCard {
                Text(
                    text = method.description,
                    style = typography.bodySmall,
                    fontWeight = FontWeight.Normal,
                    color = color.onPrimaryContainer
                )
            }
            Text(
                text = "Langkah-langkah ${method.title} :",
                style = typography.titleSmall,
                color = color.onBackground
            )
        }
        VerticalStepper(
            style = numberedVerticalWithLabel(
                totalSteps = 5,
                currentStep = 5,
                trailingLabels = listOf(
                    {
                        BaseCard {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "Mulai dengan data yang diketahui",
                                    style = typography.titleSmall,
                                    color = color.onBackground
                                )
                                Text(
                                    text = "Sistem mulai dengan sekumpulan fakta atau informasi awal",
                                    style = typography.bodySmall,
                                    color = color.onBackground,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }

                    },
                    {
                        BaseCard {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "Cek aturan-aturan yang tersedia",
                                    style = typography.titleSmall,
                                    color = color.onBackground
                                )
                                Text(
                                    text = "Setiap aturan memiliki kondisi (syarat) dan aksi (kesimpulan atau hasil).",
                                    style = typography.bodySmall,
                                    color = color.onBackground,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                    },
                    {
                        BaseCard {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "Terapkan aturan yang memenuhi kondisi",
                                    style = typography.titleSmall,
                                    color = color.onBackground
                                )
                                Text(
                                    text = "Jika syarat suatu aturan cocok dengan fakta-fakta yang ada, maka aturan tersebut dieksekusi untuk menghasilkan fakta baru.",
                                    style = typography.bodySmall,
                                    color = color.onBackground,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                    },
                    {
                        BaseCard {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "Perbarui fakta dan ulangi",
                                    style = typography.titleSmall,
                                    color = color.onBackground
                                )
                                Text(
                                    text = "Fakta baru ditambahkan ke basis pengetahuan, lalu sistem mengecek ulang apakah ada aturan lain yang sekarang bisa diterapkan.",
                                    style = typography.bodySmall,
                                    color = color.onBackground,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                    },
                    {
                        BaseCard {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "Perbarui fakta dan ulangi:",
                                    style = typography.titleSmall,
                                    color = color.onBackground
                                )
                                Text(
                                    text = "Fakta baru ditambahkan ke basis pengetahuan, lalu sistem mengecek ulang apakah ada aturan lain yang sekarang bisa diterapkan.",
                                    style = typography.bodySmall,
                                    color = color.onBackground,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                    }
                ),
                stepStyle = StepStyle(
                    stepStroke = 0f,
                    showCheckMarkOnDone = false,
                    showStrokeOnCurrent = false,
                    colors = StepDefaults(
                        doneContainerColor = color.primary,
                        doneLineColor = color.primary,
                        doneContentColor = color.onPrimary
                    )
                )
            )
        )
    }
}