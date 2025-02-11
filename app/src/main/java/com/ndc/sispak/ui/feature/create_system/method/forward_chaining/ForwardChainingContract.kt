package com.ndc.sispak.ui.feature.create_system.method.forward_chaining

import com.ndc.sispak.ui.component.textfield.TextFieldState

sealed interface ForwardChainingAction {
    data class OnChangeScreen(
        val screen: Int
    ) : ForwardChainingAction
    // Update Symptom Screen
    data class OnCodeSymptomChange(
        val index: Int,
        val code: String
    ) : ForwardChainingAction
    data class OnSymptomChange(
        val index: Int,
        val value: String
    ) : ForwardChainingAction
    data class OnCodeSymptomStateChange(
        val index: Int,
        val state: TextFieldState
    ) : ForwardChainingAction
    data class OnSymptomStateChange(
        val index: Int,
        val state: TextFieldState
    ) : ForwardChainingAction
    data object OnAddSymptom : ForwardChainingAction
    data class OnDeleteSymptom(val index: Int) : ForwardChainingAction
    data object OnSaveSymptom : ForwardChainingAction
}

sealed interface ForwardChainingEffect {

}