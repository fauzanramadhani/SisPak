package com.ndc.sispak.ui.feature.home

sealed interface HomeAction {
    data class OnSearchValueChange(
        val value: String
    ) : HomeAction
}

sealed interface HomeEffect {

}