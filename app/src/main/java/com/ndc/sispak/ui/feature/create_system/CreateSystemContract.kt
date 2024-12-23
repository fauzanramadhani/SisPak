package com.ndc.sispak.ui.feature.create_system

sealed interface CreateSystemAction {
    data class OnSearchValueChange(
        val value: String
    ) : CreateSystemAction

    data object OnBackButtonPressed: CreateSystemAction

    data object OnReload : CreateSystemAction
}

sealed interface CreateSystemEffect {
}