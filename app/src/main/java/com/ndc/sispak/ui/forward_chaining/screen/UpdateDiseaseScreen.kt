package com.ndc.sispak.ui.forward_chaining.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ndc.sispak.R
import com.ndc.sispak.common.toTwoDigitString
import com.ndc.sispak.ui.component.button.OutlinedIconButton
import com.ndc.sispak.ui.component.card.BaseCard
import com.ndc.sispak.ui.component.card.ErrorItemCard
import com.ndc.sispak.ui.component.shimmer.shimmerBrush
import com.ndc.sispak.ui.component.textfield.PrimaryTextField
import com.ndc.sispak.ui.forward_chaining.ForwardChainingAction
import com.ndc.sispak.ui.forward_chaining.ForwardChainingInputState
import com.ndc.sispak.ui.forward_chaining.ForwardChainingState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDiseaseScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    state: ForwardChainingState,
    action: (ForwardChainingAction) -> Unit,
    diseaseList: List<ForwardChainingInputState>
) {
    val ctx = LocalContext.current
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme
    val focus = LocalFocusManager.current

    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = state.loadingSwipeDiseases,
        onRefresh = {
            action(ForwardChainingAction.OnGetDisease)
        },
        state = pullToRefreshState,
        indicator = {
            Indicator(
                modifier = modifier
                    .padding(top = paddingValues.calculateTopPadding())
                    .align(Alignment.TopCenter),
                isRefreshing = state.loadingSwipeDiseases,
                state = pullToRefreshState,
                containerColor = color.primaryContainer,
                color = color.primary
            )
        }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(
                horizontal = 12.dp, vertical = 24.dp
            ),
            modifier = modifier
                .padding(paddingValues)
        ) {
            item {
                Column(
                    modifier = modifier.padding(
                        bottom = 12.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(
                        text = "Apa itu Penyakit?",
                        style = typography.titleSmall,
                        color = color.onBackground
                    )
                    BaseCard {
                        Text(
                            text = "Penyakit adalah kesimpulan atau hasil akhir yang dicapai setelah memproses sejumlah gejala dan aturan yang relevan. Penyakit merupakan tujuan akhir dari proses inferensi, di mana sistem menghubungkan gejala-gejala yang diberikan dengan aturan-aturan yang ada untuk menentukan diagnosis atau identifikasi kondisi yang tepat.",
                            style = typography.bodySmall,
                            fontWeight = FontWeight.Normal,
                            color = color.onPrimaryContainer
                        )
                    }
                }
            }
            item {
                Text(
                    text = "Masukan Data Penyakit",
                    style = typography.titleSmall,
                    color = color.onBackground,
                    modifier = modifier
                        .padding(top = 12.dp)
                )
            }
            when {
                state.loadingDiseases -> items(1) {
                    Box(
                        modifier = modifier
                            .clip(RoundedCornerShape(4.dp))
                            .fillMaxWidth()
                            .height(250.dp)
                            .background(shimmerBrush())
                    )
                }

                state.errorLoadingDiseases != null -> item {
                    ErrorItemCard(
                        message = state.errorLoadingDiseases,
                        modifier = modifier
                            .padding(horizontal = 12.dp)
                    )
                }
                else -> itemsIndexed(diseaseList) { index, item ->
                    Column(
                        modifier = modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(color.tertiaryContainer)
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.code),
                            style = typography.titleSmall,
                            color = color.onPrimaryContainer
                        )
                        Spacer(modifier = modifier.padding(top = 4.dp))
                        PrimaryTextField(
                            modifier = modifier
                                .fillMaxWidth(),
                            maxLines = 1,
                            placeholder = stringResource(id = R.string.example_placeholder, "P${(index+1).toTwoDigitString()}"),
                            value = diseaseList[index].code,
                            onValueChange = {
                                action(ForwardChainingAction.OnCodeDiseaseChange(index = index, code = it))
                            },
                            onClearValue = {
                                action(ForwardChainingAction.OnCodeDiseaseChange(index = index, code = ""))
                            },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Text,
                                capitalization = KeyboardCapitalization.Words
                            ),
                            textFieldState = diseaseList[index].stateCode
                        )
                        Spacer(modifier = modifier.padding(top = 12.dp))
                        Text(
                            text = "Penyakit",
                            style = typography.titleSmall,
                            color = color.onPrimaryContainer
                        )
                        Spacer(modifier = modifier.padding(top = 4.dp))
                        PrimaryTextField(
                            modifier = modifier
                                .fillMaxWidth(),
                            maxLines = 5,
                            placeholder = "Apnea",
                            value = diseaseList[index].value,
                            onValueChange = {
                                action(ForwardChainingAction.OnDiseaseChange(index = index, value = it))
                            },
                            onClearValue = {
                                action(ForwardChainingAction.OnDiseaseChange(index = index, value = ""))
                            },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Text,
                                capitalization = KeyboardCapitalization.Words
                            ),
                            keyboardActions = KeyboardActions(onDone = { focus.clearFocus(true) }),
                            textFieldState = diseaseList[index].stateValue
                        )
                        Spacer(modifier = modifier.padding(top = 12.dp))
                        if (diseaseList.size > 1) {
                            OutlinedIconButton(
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Delete,
                                        contentDescription = "Ikon hapus penyakit",
                                    )
                                },
                                text = "Buang",
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = color.errorContainer,
                                    contentColor = color.error,
                                    disabledContentColor = color.surfaceVariant,
                                ),
                                borderColor = color.error
                            ) {
                                action(ForwardChainingAction.OnDeleteDisease(index))
                            }
                        }
                    }
                }
            }
            item {
                OutlinedIconButton(
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Ikon hapus gejala"
                        )
                    },
                    text = "Tambah",
                    modifier = modifier.fillMaxWidth()
                ) {
                    action(ForwardChainingAction.OnAddDisease)
                }
            }
        }
    }
}