package com.ndc.sispak.ui.feature.create_system.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.binayshaw7777.kotstep.model.StepDefaults
import com.binayshaw7777.kotstep.model.StepStyle
import com.binayshaw7777.kotstep.model.numberedVerticalWithLabel
import com.binayshaw7777.kotstep.ui.vertical.VerticalStepper
import com.ndc.sispak.R
import com.ndc.sispak.ui.component.card.BaseCard
import com.ndc.sispak.ui.component.shimmer.shimmerBrush
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
                text = stringResource(id = R.string.what_is, method.title),
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
                text = stringResource(id = R.string.step_by_step, method.title),
                style = typography.titleSmall,
                color = color.onBackground
            )
        }
        when {
            state.loading -> for (i in 1..3) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .fillMaxWidth()
                        .height(75.dp)
                        .background(shimmerBrush())
                )
            }

            state.selectedMethodStep != null -> VerticalStepper(
                style = numberedVerticalWithLabel(
                    totalSteps = state.selectedMethodStep.size,
                    currentStep = state.selectedMethodStep.size,
                    trailingLabels = state.selectedMethodStep.map {
                        {
                            BaseCard {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = it.title,
                                        style = typography.titleSmall,
                                        color = color.onBackground
                                    )
                                    Text(
                                        text = it.description,
                                        style = typography.bodySmall,
                                        color = color.onBackground,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                        }
                    },
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
}