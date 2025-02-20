package com.ndc.sispak.ui.feature.create_system.method.forward_chaining

import com.ndc.sispak.ui.component.textfield.TextFieldState

sealed interface ForwardChainingAction {
    data class OnChangeScreen(
        val screen: Int
    ) : ForwardChainingAction

    // Update Symptom Screen
    data object OnGetSymptoms : ForwardChainingAction
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

    // Update Disease Screen
    data object OnGetDisease : ForwardChainingAction
    data class OnCodeDiseaseChange(
        val index: Int,
        val code: String
    ) : ForwardChainingAction

    data class OnDiseaseChange(
        val index: Int,
        val value: String
    ) : ForwardChainingAction

    data class OnCodeDiseaseStateChange(
        val index: Int,
        val state: TextFieldState
    ) : ForwardChainingAction

    data class OnDiseaseStateChange(
        val index: Int,
        val state: TextFieldState
    ) : ForwardChainingAction

    data object OnAddDisease : ForwardChainingAction
    data class OnDeleteDisease(val index: Int) : ForwardChainingAction
    data object OnSaveDisease : ForwardChainingAction
}

sealed interface ForwardChainingEffect {
    data class OnShowToast(
        val message: String
    ) : ForwardChainingEffect
}