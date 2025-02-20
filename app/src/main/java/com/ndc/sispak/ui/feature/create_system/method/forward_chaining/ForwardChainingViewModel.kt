package com.ndc.sispak.ui.feature.create_system.method.forward_chaining

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ndc.sispak.common.BaseViewModel
import com.ndc.sispak.common.ErrorMessageHandler
import com.ndc.sispak.common.UiStatus
import com.ndc.sispak.data.remote.body.forward_chaining.DiseaseBody
import com.ndc.sispak.data.remote.body.forward_chaining.SymptomBody
import com.ndc.sispak.domain.ForwardChainingGetDiseasesUseCase
import com.ndc.sispak.domain.ForwardChainingGetSymptomsUseCase
import com.ndc.sispak.domain.ForwardChainingUpdateDiseasesUseCase
import com.ndc.sispak.domain.ForwardChainingUpdateSymptomsUseCase
import com.ndc.sispak.ui.component.textfield.TextFieldState
import com.ndc.sispak.ui.navigation.NavGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForwardChainingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val forwardChainingGetSymptomsUseCase: ForwardChainingGetSymptomsUseCase,
    private val forwardChainingUpdateSymptomsUseCase: ForwardChainingUpdateSymptomsUseCase,
    private val forwardChainingGetDiseasesUseCase: ForwardChainingGetDiseasesUseCase,
    private val forwardChainingUpdateDiseasesUseCase: ForwardChainingUpdateDiseasesUseCase,
    private val errorMessageHandler: ErrorMessageHandler
) : BaseViewModel<ForwardChainingState, ForwardChainingAction, ForwardChainingEffect>(
    ForwardChainingState()
) {
    private val _symptoms = mutableStateListOf(ForwardChainingInputState())
    val symptoms: List<ForwardChainingInputState> get() = _symptoms

    private val _diseases = mutableStateListOf(ForwardChainingInputState())
    val diseases: List<ForwardChainingInputState> get() = _diseases

    init {
        val authParams = savedStateHandle.toRoute<NavGraph.ForwardChainingScreen>()
        updateState {
            copy(systemId = authParams.systemId)
        }
        if (uiState.value.screen == 0) {
            getSymptoms()
        }
    }

    override fun onAction() {
        on(ForwardChainingAction.OnChangeScreen::class.java) {
            onChangeScreen(this@on.screen)
        }
        // Symptom
        on(ForwardChainingAction.OnGetSymptoms::class.java) {
            updateState { copy(loadingSwipeSymptoms = true) }
            getSymptoms()
        }
        on(ForwardChainingAction.OnCodeSymptomChange::class.java) {
            val oldCode = symptoms[this@on.index].code
            _symptoms[this@on.index] = symptoms[this@on.index].copy(
                code = this@on.code,
                stateCode = TextFieldState.Empty
            )
            val duplicateIndices = symptoms.indices.filter { symptoms[it].code == oldCode }
            if (duplicateIndices.size < 2) {
                duplicateIndices.forEach {
                    _symptoms[it] = symptoms[it].copy(stateCode = TextFieldState.Empty)
                }
            }
        }
        on(ForwardChainingAction.OnSymptomChange::class.java) {
            _symptoms[this@on.index] = symptoms[this@on.index].copy(value = this@on.value)
            if (symptoms[this@on.index].stateValue is TextFieldState.Error) {
                _symptoms[this@on.index] =
                    symptoms[this@on.index].copy(stateValue = TextFieldState.Empty)
            }
        }
        on(ForwardChainingAction.OnCodeSymptomStateChange::class.java) {
            _symptoms[this@on.index] = symptoms[this@on.index].copy(stateCode = this@on.state)
        }
        on(ForwardChainingAction.OnSymptomStateChange::class.java) {
            _symptoms[this@on.index] = symptoms[this@on.index].copy(stateValue = this@on.state)
        }
        on(ForwardChainingAction.OnAddSymptom::class.java) {
            _symptoms.add(ForwardChainingInputState())
        }
        on(ForwardChainingAction.OnDeleteSymptom::class.java) {
            _symptoms.removeAt(this@on.index)
        }
        on(ForwardChainingAction.OnSaveSymptom::class.java) {
            updateSymptoms()
        }
        // Disease
        on(ForwardChainingAction.OnGetDisease::class.java) {
            updateState { copy(loadingSwipeDiseases = true) }
            getDiseases()
        }
        on(ForwardChainingAction.OnCodeDiseaseChange::class.java) {
            val oldCode = diseases[this@on.index].code
            _diseases[this@on.index] = diseases[this@on.index].copy(
                code = this@on.code,
                stateCode = TextFieldState.Empty
            )
            val duplicateIndices = diseases.indices.filter { diseases[it].code == oldCode }
            if (duplicateIndices.size < 2) {
                duplicateIndices.forEach {
                    _diseases[it] = diseases[it].copy(stateCode = TextFieldState.Empty)
                }
            }
        }
        on(ForwardChainingAction.OnDiseaseChange::class.java) {
            _diseases[this@on.index] = diseases[this@on.index].copy(value = this@on.value)
            if (diseases[this@on.index].stateValue is TextFieldState.Error) {
                _diseases[this@on.index] =
                    diseases[this@on.index].copy(stateValue = TextFieldState.Empty)
            }
        }
        on(ForwardChainingAction.OnCodeDiseaseStateChange::class.java) {
            _diseases[this@on.index] = diseases[this@on.index].copy(stateCode = this@on.state)
        }
        on(ForwardChainingAction.OnDiseaseStateChange::class.java) {
            _diseases[this@on.index] = diseases[this@on.index].copy(stateValue = this@on.state)
        }
        on(ForwardChainingAction.OnAddDisease::class.java) {
            _diseases.add(ForwardChainingInputState())
        }
        on(ForwardChainingAction.OnDeleteDisease::class.java) {
            _diseases.removeAt(this@on.index)
        }
        on(ForwardChainingAction.OnSaveDisease::class.java) {
            updateDisease()
        }
    }

    private fun updateDisease() = viewModelScope.launch {
        val state = uiState.value
        val disease = diseases.map { DiseaseBody(code = it.code, description = it.value) }

        forwardChainingUpdateDiseasesUseCase.invoke(
            systemId = state.systemId,
            diseases = disease
        ).onStart {
            updateState { copy(loadingBottomBar = true) }
        }.onEach {
            when (it) {
                is UiStatus.Error -> {
                    send(ForwardChainingEffect.OnShowToast(errorMessageHandler.fromCode(it.code)))
                    Log.e(ForwardChainingViewModel::class.simpleName, it.message)
                }

                is UiStatus.Success -> {
                    onChangeScreen(2)
                }
            }
        }.onCompletion {
            updateState { copy(loadingBottomBar = false) }
        }.collect()
    }

    private fun getDiseases() = viewModelScope.launch {
        val state = uiState.value
        forwardChainingGetDiseasesUseCase.invoke(state.systemId)
            .onStart {
                _diseases.clear()
                updateState {
                    copy(
                        errorLoadingDiseases = null,
                        loadingDiseases = true,
                    )
                }
            }.onEach { response ->
                when (response) {
                    is UiStatus.Error -> updateState {
                        copy(
                            errorLoadingDiseases = errorMessageHandler.fromCode(
                                response.code
                            )
                        )
                    }

                    is UiStatus.Success -> {
                        val diseases = response.data
                        if (diseases.isNullOrEmpty()) {
                            _diseases.add(ForwardChainingInputState())
                        } else {
                            _diseases.addAll(diseases.map {
                                ForwardChainingInputState(
                                    id = it.id,
                                    code = it.code,
                                    value = it.description,
                                )
                            })
                        }
                    }
                }
            }.onCompletion {
                updateState {
                    copy(
                        loadingDiseases = false,
                        loadingSwipeDiseases = false
                    )
                }
            }.collect()
    }

    private fun getSymptoms() = viewModelScope.launch {
        val state = uiState.value
        forwardChainingGetSymptomsUseCase.invoke(state.systemId)
            .onStart {
                _symptoms.clear()
                updateState {
                    copy(
                        errorLoadingSymptoms = null,
                        loadingSymptoms = true,
                    )
                }
            }.onEach { response ->
                when (response) {
                    is UiStatus.Error -> updateState {
                        copy(
                            errorLoadingSymptoms = errorMessageHandler.fromCode(
                                response.code
                            )
                        )
                    }

                    is UiStatus.Success -> {
                        val symptoms = response.data
                        if (symptoms.isNullOrEmpty()) {
                            _symptoms.add(ForwardChainingInputState())
                        } else {
                            _symptoms.addAll(symptoms.map {
                                ForwardChainingInputState(
                                    id = it.id,
                                    code = it.code,
                                    value = it.description,
                                )
                            })
                        }
                    }
                }
            }.onCompletion {
                updateState {
                    copy(
                        loadingSymptoms = false,
                        loadingSwipeSymptoms = false
                    )
                }
            }.collect()
    }

    private fun updateSymptoms() = viewModelScope.launch {
        val state = uiState.value
        val symptoms = symptoms.map { SymptomBody(code = it.code, description = it.value) }

        forwardChainingUpdateSymptomsUseCase.invoke(
            systemId = state.systemId,
            symptoms = symptoms
        ).onStart {
            updateState { copy(loadingBottomBar = true) }
        }.onEach {
            when (it) {
                is UiStatus.Error -> {
                    send(ForwardChainingEffect.OnShowToast(errorMessageHandler.fromCode(it.code)))
                    Log.e(ForwardChainingViewModel::class.simpleName, it.message)
                }

                is UiStatus.Success -> onChangeScreen(1)
            }
        }.onCompletion {
            updateState { copy(loadingBottomBar = false) }
        }.collect()
    }

    private fun onChangeScreen(screen: Int) = viewModelScope.launch {
        when (screen) {
            0 -> {
                getSymptoms()
            }

            1 -> {
                getDiseases()
            }
        }
        updateState { copy(screen = screen) }
        updateState { copy(navigateButton = false) }
        delay(1000)
        updateState { copy(navigateButton = true) }
    }
}