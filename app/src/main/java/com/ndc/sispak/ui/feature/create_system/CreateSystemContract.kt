package com.ndc.sispak.ui.feature.create_system

sealed interface CreateSystemAction {
    data class OnSearchValueChange(
        val value: String
    ) : CreateSystemAction

    data object OnBackButtonPressed: CreateSystemAction

    data object OnReload : CreateSystemAction

    data class OnMethodSelected(
        val methodId: Int
    ) : CreateSystemAction

    data object OnBottomAppAction : CreateSystemAction
}

sealed interface CreateSystemEffect {
}