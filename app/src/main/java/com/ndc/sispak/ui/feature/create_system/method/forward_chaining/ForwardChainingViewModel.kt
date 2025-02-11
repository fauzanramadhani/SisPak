package com.ndc.sispak.ui.feature.create_system.method.forward_chaining

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ndc.sispak.common.BaseViewModel
import com.ndc.sispak.ui.component.textfield.TextFieldState
import com.ndc.sispak.ui.navigation.NavGraph
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForwardChainingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<ForwardChainingState, ForwardChainingAction, ForwardChainingEffect>(
    ForwardChainingState()
) {
    private val _symptoms = mutableStateListOf(ForwardChainingInputState())
    val symptoms: List<ForwardChainingInputState> get() = _symptoms

    init {
        val authParams = savedStateHandle.toRoute<NavGraph.ForwardChainingScreen>()
        updateState {
            copy(systemId = authParams.systemId)
        }
        onChangeScreen(authParams.screen)
    }

    override fun onAction() {
        on(ForwardChainingAction.OnChangeScreen::class.java) {
            onChangeScreen(this@on.screen)
        }
        on(ForwardChainingAction.OnCodeSymptomChange::class.java) {
            val oldCode = _symptoms[this@on.index].code
            symptoms.forEachIndexed { idx, each ->
                if (each.code.lowercase() == oldCode.lowercase()) {
                    _symptoms[idx] =
                        symptoms[idx].copy(stateCode = TextFieldState.Empty)
                }
            }
            if (symptoms[this@on.index].stateCode is TextFieldState.Error) {
                _symptoms[this@on.index] =
                    symptoms[this@on.index].copy(stateCode = TextFieldState.Empty)
            }
            _symptoms[this@on.index] = symptoms[this@on.index].copy(code = this@on.code)
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
    }

    private fun onChangeScreen(screen: Int) = viewModelScope.launch {
        updateState { copy(screen = screen) }
        if (screen == 2) updateState { copy(bottomAppBarVisible = false) }
        else updateState { copy(bottomAppBarVisible = true) }
        updateState { copy(navigateButton = false) }
        delay(1000)
        updateState { copy(navigateButton = true) }
    }
}