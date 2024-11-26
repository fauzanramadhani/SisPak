package com.ndc.sispak.ui.feature.auth

import android.util.Log
import com.ndc.sispak.common.BaseViewModel
import javax.inject.Inject

class AuthViewModel @Inject constructor(

) : BaseViewModel<AuthState, AuthAction, AuthEffect>(
    AuthState()
) {
    override fun onAction() {
        on(AuthAction.OnScreenChange::class.java) {
            updateState { copy(screen = this@on.screen) }
        }
        // Login
        on(AuthAction.OnLoginEmailValueChange::class.java) {
            updateState { copy(loginEmailValue = value) }
        }
        on(AuthAction.OnLoginPasswordValueChange::class.java) {
            updateState { copy(loginPasswordValue = value) }
        }
        on(AuthAction.OnLoginPasswordVisibilityChange::class.java) {
            updateState { copy(loginPasswordVisible = visible) }
        }
        on(AuthAction.OnLoginEmailStateChange::class.java) {
            updateState { copy(loginEmailState = state) }
        }
        on(AuthAction.OnLoginPasswordStateChange::class.java) {
            updateState { copy(loginPasswordState = state) }
        }
        // Register
        on(AuthAction.OnRegisterEmailStateChange::class.java) {
            updateState { copy(registerEmailState = state) }
        }
        on(AuthAction.OnRegisterEmailValueChange::class.java) {
            updateState { copy(registerEmailValue = value) }
        }
        on(AuthAction.OnRegisterPasswordConfirmationStateChange::class.java) {
            updateState { copy(registerPasswordConfirmationState = state) }
        }
        on(AuthAction.OnRegisterPasswordConfirmationValueChange::class.java) {
            updateState { copy(registerPasswordConfirmationValue = value) }
        }
        on(AuthAction.OnRegisterPasswordConfirmationVisibilityChange::class.java) {
            updateState { copy(registerPasswordConfirmationVisible = visible) }
        }
        on(AuthAction.OnRegisterPasswordStateChange::class.java) {
            updateState { copy(registerPasswordState = state) }
        }
        on(AuthAction.OnRegisterPasswordValueChange::class.java) {
            updateState { copy(registerPasswordValue = value) }
        }
        on(AuthAction.OnRegisterPasswordVisibilityChange::class.java) {
            updateState { copy(registerPasswordVisible = visible) }
        }
        on(AuthAction.OnHandleLoginWithGoogle::class.java) {
            // TODO
        }
        on(AuthAction.OnLoginBasic::class.java) {
            // TODO
        }
        on(AuthAction.OnRegister::class.java) {
            // TODO
        }
    }

}