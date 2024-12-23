package com.ndc.sispak.ui.feature.home

sealed interface HomeAction {
    data class OnSelectedScreenChange(
        val screen: Int
    ) : HomeAction
    data object OnGetUserInfo : HomeAction
    data object OnLogout : HomeAction
}

sealed interface HomeEffect {
    data class OnShowToast(
        val message: String
    ) : HomeEffect
    data object OnLogout : HomeEffect
}