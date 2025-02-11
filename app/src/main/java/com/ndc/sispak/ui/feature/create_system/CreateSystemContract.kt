package com.ndc.sispak.ui.feature.create_system

sealed interface CreateSystemAction {

    data class OnSearchValueChange(
        val value: String
    ) : CreateSystemAction

    data object OnReloadMethod : CreateSystemAction

    data class OnMethodSelected(
        val methodId: Int
    ) : CreateSystemAction

    data class OnChangeScreen(
        val screen: Int,
    ) : CreateSystemAction

    // Create Name Screen
    data class OnCreateNameTitleValueChange(
        val value: String
    ) : CreateSystemAction
    data class OnCreateNameSummaryValueChange(
        val value: String
    ) : CreateSystemAction
    data object OnCreateNameSave : CreateSystemAction
}

sealed interface CreateSystemEffect {
    //Create Name Screen
    data class OnShowToast(
        val message: String
    ) : CreateSystemEffect
    data class OnSave(
        val methodId: Int,
        val systemId: Int
    ) : CreateSystemEffect

}