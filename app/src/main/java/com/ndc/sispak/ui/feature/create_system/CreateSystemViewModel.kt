package com.ndc.sispak.ui.feature.create_system

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ndc.sispak.R
import com.ndc.sispak.common.BaseViewModel
import com.ndc.sispak.common.ErrorMessageHandler
import com.ndc.sispak.common.UiStatus
import com.ndc.sispak.domain.CreateSystemUseCase
import com.ndc.sispak.domain.GetMethodStepUseCase
import com.ndc.sispak.domain.GetMethodUseCase
import com.ndc.sispak.ui.component.textfield.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateSystemViewModel @Inject constructor(
    private val getMethodUseCase: GetMethodUseCase,
    private val errorMessageHandler: ErrorMessageHandler,
    private val getMethodStepUseCase: GetMethodStepUseCase,
    private val createSystemUseCase: CreateSystemUseCase,
    @ApplicationContext private val context: Context
) : BaseViewModel<CreateSystemState, CreateSystemAction, CreateSystemEffect>(
    CreateSystemState()
) {

    init {
        getMethod()
    }

    override fun onAction() {
        on(CreateSystemAction.OnSearchValueChange::class.java) {
            updateState { copy(searchValue = value) }
            findExpertSystem(value)
        }
        on(CreateSystemAction.OnReloadMethod::class.java) {
            getMethod()
        }
        on(CreateSystemAction.OnChangeScreen::class.java) {
            onChangeScreen(this.screen)
        }
        on(CreateSystemAction.OnMethodSelected::class.java) {
            updateState {
                copy(
                    screen = 1,
                    bottomAppBarVisible = true,
                    bottomAppBarTitle = R.string.next,
                    selectedMethod = uiState.value.method.find { it.id == this@on.methodId },
                    selectedMethodStep = null,
                )
            }
            getMethodStep(this@on.methodId)
        }
        on(CreateSystemAction.OnCreateNameTitleValueChange::class.java) {
            if (this@on.value.length == 1) {
                updateState {
                    copy(
                        createNameTitleState = TextFieldState.Empty
                    )
                }
            }
            updateState { copy(createNameTitleValue = this@on.value) }
        }
        on(CreateSystemAction.OnCreateNameSummaryValueChange::class.java) {
            updateState { copy(createNameSummaryValue = this@on.value) }
        }
        on(CreateSystemAction.OnCreateNameSave::class.java) {
            val state = uiState.value
            when {
                state.createNameTitleValue.isEmpty() -> updateState {
                    copy(
                        createNameTitleState = TextFieldState.Error(context.getString(R.string.empty_system_title))
                    )
                }
                else -> createSystem()
            }

        }
    }

    private fun getMethodStep(methodId: Int) = viewModelScope.launch {
        getMethodStepUseCase.invoke(methodId)
            .onStart {
                updateState { copy(loading = true) }
            }
            .onEach {
                when (it) {
                    is UiStatus.Error -> {
                        Log.e(CreateSystemViewModel::class.simpleName, it.message)
                        throw Exception()
                    }

                    is UiStatus.Success -> updateState { copy(selectedMethodStep = it.data) }
                }

            }
            .retry()
            .onCompletion {
                updateState { copy(loading = false) }
            }
            .collect()
    }

    private fun onChangeScreen(screen: Int) = viewModelScope.launch {
        updateState { copy(screen = screen) }
        when (screen) {
            0 -> updateState { copy(bottomAppBarVisible = false) }
            2 -> updateState { copy(bottomAppBarVisible = true) }
        }
        updateState { copy(navigateButton = false) }
        delay(1000)
        updateState { copy(navigateButton = true) }
    }

    private fun findExpertSystem(keyword: String) {
        if (keyword.isEmpty()) {
            updateState { copy(methodVisible = method) }
        } else {
            val newExpertSystemVisible = uiState.value.method
                .toMutableList()
                .filter { item ->
                    item.title.contains(keyword, true)
                }
            updateState { copy(methodVisible = newExpertSystemVisible) }
        }
    }

    private fun getMethod() = viewModelScope.launch {
        getMethodUseCase.invoke()
            .onStart {
                updateState {
                    copy(
                        loading = true,
                        errorLoadMethod = ""
                    )
                }
            }.onEach {
                updateState {
                    copy(
                        loading = false,
                    )
                }
                when (it) {
                    is UiStatus.Error -> {
                        updateState { copy(errorLoadMethod = errorMessageHandler.fromCode(it.code)) }
                        Log.e(CreateSystemViewModel::class.simpleName, it.message)
                    }

                    is UiStatus.Success -> {
                        val data = it.data ?: listOf()
                        updateState {
                            copy(
                                method = data,
                                methodVisible = data
                            )
                        }
                    }
                }
            }.collect()
    }

    private fun createSystem() = viewModelScope.launch {
        val state = uiState.value
        val selectedMethodId = state.selectedMethod?.id ?: 1
        createSystemUseCase.invoke(
            methodId = selectedMethodId,
            title = state.createNameTitleValue,
            description = state.createNameSummaryValue,
        )
            .onStart {
                updateState { copy(loadingSaveName = true) }
            }.onEach {
                when (it) {
                    is UiStatus.Error -> {
                        showToast(errorMessageHandler.fromCode(it.code))
                        Log.e(CreateSystemViewModel::class.simpleName, it.message)
                        updateState { copy(loadingSaveName = false) }
                    }

                    is UiStatus.Success -> {
                        val data = it.data ?: 0
                        onSave(methodId = selectedMethodId, systemId = data)
                    }
                }
            }.collect()
    }

    private fun showToast(message: String) = sendEffect(CreateSystemEffect.OnShowToast(message))

    private fun onSave(
        methodId: Int,
        systemId: Int
    )= sendEffect(CreateSystemEffect.OnSave(methodId, systemId))
}