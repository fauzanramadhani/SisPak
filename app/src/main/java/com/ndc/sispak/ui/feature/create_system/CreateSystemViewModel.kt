package com.ndc.sispak.ui.feature.create_system

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ndc.sispak.R
import com.ndc.sispak.common.BaseViewModel
import com.ndc.sispak.common.ErrorMessageHandler
import com.ndc.sispak.common.UiStatus
import com.ndc.sispak.domain.GetMethodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateSystemViewModel @Inject constructor(
    private val getMethodUseCase: GetMethodUseCase,
    private val errorMessageHandler: ErrorMessageHandler
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
        on(CreateSystemAction.OnReload::class.java) {
            getMethod()
        }
        on(CreateSystemAction.OnBackButtonPressed::class.java) {
            onBackButtonPressed()
        }
        on(CreateSystemAction.OnMethodSelected::class.java) {
            updateState {
                copy(
                    screen = 1,
                    bottomAppBarVisible = true,
                    bottomAppBarTitle = R.string.next,
                    selectedMethod = uiState.value.method.find { it.id == this@on.methodId }
                )
            }
        }
    }

    private fun onBackButtonPressed() = viewModelScope.launch {
        val currentScreen = uiState.value.screen
        if (currentScreen != 0) {
            updateState { copy(screen = currentScreen - 1) }
        }
        when (currentScreen) {
            1 -> updateState { copy(bottomAppBarVisible = false) }
        }
        updateState { copy(backButtonEnabled = false) }
        delay(1000)
        updateState { copy(backButtonEnabled = true) }
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
}