package com.ndc.sispak.ui.forward_chaining

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.ndc.sispak.R
import com.ndc.sispak.common.Either
import com.ndc.sispak.common.MakeToast
import com.ndc.sispak.ui.component.app_bar.BottomSecondaryAppBar
import com.ndc.sispak.ui.component.app_bar.TopSecondaryAppBar
import com.ndc.sispak.ui.component.textfield.TextFieldState
import com.ndc.sispak.ui.forward_chaining.screen.UpdateDiseaseScreen
import com.ndc.sispak.ui.forward_chaining.screen.UpdateRelationScreen
import com.ndc.sispak.ui.forward_chaining.screen.UpdateSymptomScreen
import com.ndc.sispak.ui.navigation.NavGraph
import kotlinx.coroutines.flow.Flow

@Composable
fun ForwardChainingScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    stateFlow: Flow<ForwardChainingState>,
    effectFlow: Flow<Either<ForwardChainingEffect>>,
    action: (ForwardChainingAction) -> Unit,
    symptomList: List<ForwardChainingInputState>,
    diseaseList: List<ForwardChainingInputState>
) {
    val ctx = LocalContext.current
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme

    val state by stateFlow.collectAsState(initial = ForwardChainingState())
    val effect by effectFlow.collectAsState(initial = Either.left())

    fun validateSymptom(
    ): Boolean {
        var hasError = false
        val codeMap = mutableMapOf<String, MutableList<Int>>()

        symptomList.forEachIndexed { index, data ->
            var isValid = true

            if (data.code.isEmpty()) {
                action(
                    ForwardChainingAction.OnCodeSymptomStateChange(
                        index, TextFieldState.Error("Silahkan lengkapi kode gejala pada field ini")
                    )
                )
                isValid = false
            }

            if (data.value.isEmpty()) {
                action(
                    ForwardChainingAction.OnSymptomStateChange(
                        index, TextFieldState.Error("Silahkan lengkapi gejala pada field ini")
                    )
                )
                isValid = false
            }

            if (data.code.isNotEmpty()) {
                codeMap.computeIfAbsent(data.code.lowercase()) { mutableListOf() }.add(index)
            }

            if (!isValid) hasError = true
        }

        codeMap.forEach { (code, indices) ->
            if (indices.size > 1) {
                indices.forEach { index ->
                    action(
                        ForwardChainingAction.OnCodeSymptomStateChange(
                            index,
                            TextFieldState.Error("Kode gejala '${code.uppercase()}' sudah digunakan. Harap gunakan kode lain.")
                        )
                    )
                }
                hasError = true
            }
        }

        return !hasError
    }

    fun validateDisease(
    ): Boolean {
        var hasError = false
        val codeMap = mutableMapOf<String, MutableList<Int>>()

        diseaseList.forEachIndexed { index, data ->
            var isValid = true

            if (data.code.isEmpty()) {
                action(
                    ForwardChainingAction.OnCodeDiseaseStateChange(
                        index,
                        TextFieldState.Error("Silahkan lengkapi kode penyakit pada field ini")
                    )
                )
                isValid = false
            }

            if (data.value.isEmpty()) {
                action(
                    ForwardChainingAction.OnDiseaseStateChange(
                        index, TextFieldState.Error("Silahkan lengkapi penyakit pada field ini")
                    )
                )
                isValid = false
            }

            if (data.code.isNotEmpty()) {
                codeMap.computeIfAbsent(data.code.lowercase()) { mutableListOf() }.add(index)
            }

            if (!isValid) hasError = true
        }

        codeMap.forEach { (code, indices) ->
            if (indices.size > 1) {
                indices.forEach { index ->
                    action(
                        ForwardChainingAction.OnCodeDiseaseStateChange(
                            index,
                            TextFieldState.Error("Kode penyakit '${code.uppercase()}' sudah digunakan. Harap gunakan kode lain.")
                        )
                    )
                }
                hasError = true
            }
        }

        return !hasError
    }


    LaunchedEffect(key1 = effect) {
        effect.onRight {
            when (it) {
                is ForwardChainingEffect.OnShowToast -> MakeToast(ctx).short(it.message)
            }
        }
    }

    BackHandler {
    }

    Scaffold(
        topBar = {
            TopSecondaryAppBar(
                enabled = state.navigateButton,
                onBackPressed = {
                    when (state.screen) {
                        0 -> {
                            navHostController.navigate(NavGraph.HomeScreen)
                        }

                        else -> action(ForwardChainingAction.OnChangeScreen(state.screen - 1))
                    }
                }
            )
        },
        bottomBar = {
            if (state.bottomAppBarVisible) {
                BottomSecondaryAppBar(
                    loading = state.loadingBottomBar,
                    title = when (state.screen) {
                        2 -> stringResource(id = R.string.save)
                        else -> stringResource(id = R.string.next)
                    },
                    onNextPressed = {
                        when (state.screen) {
                            0 -> {
                                if (validateSymptom()) {
                                    action(ForwardChainingAction.OnSaveSymptom)
                                }
                            }

                            1 -> {
                                if (validateDisease()) {
                                    action(ForwardChainingAction.OnSaveDisease)
                                }
                            }

                        }
                    },
                )
            }
        }
    ) { paddingValues ->
        Image(
            painter = painterResource(id = R.drawable.bg_white),
            contentScale = ContentScale.Crop,
            contentDescription = "",
            modifier = modifier
                .fillMaxSize()
        )
        when (state.screen) {
            0 -> UpdateSymptomScreen(
                modifier = modifier,
                paddingValues = paddingValues,
                state = state,
                action = action,
                symptomList = symptomList
            )

            1 -> UpdateDiseaseScreen(
                modifier = modifier,
                paddingValues = paddingValues,
                state = state,
                action = action,
                diseaseList = diseaseList
            )
            2 -> UpdateRelationScreen(
                modifier = modifier,
                paddingValues = paddingValues,
                state = state,
                action = action,
            )
        }
    }
}