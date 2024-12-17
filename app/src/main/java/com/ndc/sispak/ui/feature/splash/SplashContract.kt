package com.ndc.sispak.ui.feature.splash

sealed interface SplashAction {
    data object CheckAuthStatus : SplashAction
}

sealed interface SplashEffect {
    data object NavigateToHome : SplashEffect
    data object NavigateToAuth : SplashEffect
    data object NavigateToPersonalization : SplashEffect
    data class OnError(
        val message: String
    ) : SplashEffect
}