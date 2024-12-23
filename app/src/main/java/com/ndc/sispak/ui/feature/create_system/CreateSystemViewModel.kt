package com.ndc.sispak.ui.feature.create_system

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ndc.sispak.common.BaseViewModel
import com.ndc.sispak.common.ErrorMessageHandler
import com.ndc.sispak.common.UiStatus
import com.ndc.sispak.domain.GetMethodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateSystemViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
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
    }

    private fun onBackButtonPressed() = viewModelScope.launch {
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