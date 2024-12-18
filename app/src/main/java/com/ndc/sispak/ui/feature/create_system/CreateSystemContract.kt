package com.ndc.sispak.ui.feature.create_system

sealed interface CreateSystemAction {
    data class OnSearchValueChange(
        val value: String
    ) : CreateSystemAction
}

sealed interface CreateSystemEffect {

}