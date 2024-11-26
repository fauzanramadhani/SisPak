package com.ndc.sispak.ui.feature.splash

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ndc.sispak.common.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(

): BaseViewModel<SplashState, SplashAction, SplashEffect>(
    SplashState()
) {

    init {
        navigateWithDelay()
    }
    override fun onAction() {

    }

    private fun navigateWithDelay() = viewModelScope.launch {
        delay(1000)
        send(SplashEffect.NavigateToMain)
    }
}