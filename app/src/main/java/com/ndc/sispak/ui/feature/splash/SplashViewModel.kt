package com.ndc.sispak.ui.feature.splash

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ndc.sispak.common.BaseViewModel
import com.ndc.sispak.common.ErrorMessageHandler
import com.ndc.sispak.common.UiStatus
import com.ndc.sispak.domain.GetFirebaseUserUseCase
import com.ndc.sispak.domain.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getFirebaseUserUseCase: GetFirebaseUserUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val errorMessageHandler: ErrorMessageHandler
) : BaseViewModel<SplashState, SplashAction, SplashEffect>(
    SplashState()
) {

    init {
        checkAuthStatus()
    }

    override fun onAction() {
        on(SplashAction.CheckAuthStatus::class.java) {
            checkAuthStatus()
        }
    }

    private fun checkAuthStatus() = viewModelScope.launch {
        updateState { copy(reloadIconVisibility = false) }

        val currentUserId = getFirebaseUserUseCase.invoke()?.uid

        if (currentUserId == null) {
            send(SplashEffect.NavigateToAuth)
        } else {
            getUserInfoUseCase.invoke()
                .onEach { uiStatus ->
                    when (uiStatus) {
                        is UiStatus.Error -> handleError(uiStatus)

                        is UiStatus.Success -> send(SplashEffect.NavigateToHome)
                    }
                }
                .collect()
        }
    }

    private fun handleError(error: UiStatus.Error) {
        when (error.code) {
            461 -> send(SplashEffect.NavigateToPersonalization)

            else -> {
                updateState { copy(reloadIconVisibility = true) }
                val errorMessage = errorMessageHandler.fromCode(error.code)
                send(SplashEffect.OnError(errorMessage))
                Log.e(SplashViewModel::class.simpleName, error.message)
            }
        }
    }
}