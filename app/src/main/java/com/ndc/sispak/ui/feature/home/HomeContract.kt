package com.ndc.sispak.ui.feature.home

sealed interface HomeAction {
    data class OnSelectedScreenChange(
        val screen: Int
    ) : HomeAction
}

sealed interface HomeEffect {

}