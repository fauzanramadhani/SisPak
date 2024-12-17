package com.ndc.sispak.ui.feature.auth

import android.content.Intent
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.ndc.sispak.common.BaseViewModel
import com.ndc.sispak.domain.GetFirebaseUserUseCase
import com.ndc.sispak.domain.HandleLoginWithGoogleUseCase
import com.ndc.sispak.domain.LoginBasicUseCase
import com.ndc.sispak.domain.LogoutUseCase
import com.ndc.sispak.domain.RegisterUseCase
import com.ndc.sispak.ui.navigation.NavGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val handleLoginWithGoogleUseCase: HandleLoginWithGoogleUseCase,
    private val loginBasicUseCase: LoginBasicUseCase,
    private val registerUseCase: RegisterUseCase,
    private val googleSignInClient: GoogleSignInClient,
    private val logoutUseCase: LogoutUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<AuthState, AuthAction, AuthEffect>(
    AuthState()
) {
    init {
        val authParams = savedStateHandle.toRoute<NavGraph.AuthScreen>()
        Log.e("params", authParams.screen.toString())
        updateState {
            copy(
                screen = authParams.screen,
                gso = googleSignInClient.signInIntent
            )
        }
    }

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
        on(AuthAction.OnHandleLoginWithGoogle::class.java) {
            handleLoginWithGoogle(this.intent)
        }
        on(AuthAction.OnLoginBasic::class.java) {
            loginBasic()
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
        on(AuthAction.OnRegister::class.java) {
            register()
        }
        // Personalization
        on(AuthAction.OnPersonalizationDobValueChange::class.java) {
            updateState { copy(personalizationDobValue = value) }
        }
        on(AuthAction.OnPersonalizationDobStateChange::class.java) {
            updateState { copy(personalizationDobState = state) }
        }
        on(AuthAction.OnPersonalizationNameValueChange::class.java) {
            updateState { copy(personalizationNameValue = value) }
        }
        on(AuthAction.OnPersonalizationNameStateChange::class.java) {
            updateState { copy(personalizationNameState = state) }
        }
        on(AuthAction.OnSavePersonalization::class.java) {
            // TODO
        }
    }

    private fun logout() = viewModelScope.launch {
        logoutUseCase.invoke()
        updateState { copy(screen = 0) }
    }

    private fun handleLoginWithGoogle(intent: Intent) = viewModelScope.launch {
        updateState { copy(loadingState = true) }
        try {
            handleLoginWithGoogleUseCase.invoke(intent).addOnSuccessListener {
                updateState { copy(screen = 2) }
            }.addOnFailureListener { e ->
                updateState { copy(loadingState = false) }
                send(AuthEffect.OnShowToast(e.message.toString()))
            }
        } catch (e: Exception) {
            when (e.message.toString()) {
                "12501: " -> sendEffect(AuthEffect.OnShowToast("Operation Canceled by User"))
                else -> {
                    send(AuthEffect.OnShowToast(e.message.toString()))
                }
            }
            updateState { copy(loadingState = false) }
        }
    }

    private fun loginBasic() = viewModelScope.launch {
        updateState { copy(loadingState = true) }
        loginBasicUseCase.invoke(
            uiState.value.loginEmailValue,
            uiState.value.loginPasswordValue
        ).addOnSuccessListener { _ ->
            updateState { copy(screen = 2) }
        }.addOnFailureListener {
            send(AuthEffect.OnShowToast(it.message.toString()))
            updateState { copy(loadingState = false) }
        }
    }

    private fun register() = viewModelScope.launch {
        updateState { copy(loadingState = true) }
        registerUseCase.invoke(
            uiState.value.registerEmailValue,
            uiState.value.registerPasswordValue
        ).addOnSuccessListener { _ ->
            updateState {
                copy(
                    loadingState = false,
                    screen = 2
                )
            }

        }.addOnFailureListener {
            updateState { copy(loadingState = false) }
            send(AuthEffect.OnShowToast(it.message.toString()))
        }
    }
}