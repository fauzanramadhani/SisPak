package com.ndc.sispak.ui.feature.splash

sealed interface SplashAction {

}

sealed interface SplashEffect {
    data object NavigateToMain : SplashEffect
}