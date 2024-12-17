package com.ndc.sispak.ui.feature.auth

import android.content.Intent
import com.ndc.sispak.ui.component.textfield.TextFieldState

sealed interface AuthAction {
    data class OnScreenChange(
        val screen: Int
    ) : AuthAction

    // Login
    data class OnLoginEmailValueChange(
        val value: String
    ) : AuthAction
    data class OnLoginPasswordValueChange(
        val value: String
    ) : AuthAction
    data class OnLoginPasswordVisibilityChange(
        val visible: Boolean
    ) : AuthAction
    data class OnLoginEmailStateChange(
        val state: TextFieldState
    ) : AuthAction
    data class OnLoginPasswordStateChange(
        val state: TextFieldState
    ) : AuthAction
    data object OnLoginBasic : AuthAction
    data class OnHandleLoginWithGoogle(
        val intent: Intent
    ) : AuthAction

    // Register
    data class OnRegisterEmailValueChange(
        val value: String
    ) : AuthAction
    data class OnRegisterPasswordValueChange(
        val value: String
    ) : AuthAction
    data class OnRegisterPasswordConfirmationValueChange(
        val value: String
    ) : AuthAction
    data class OnRegisterEmailStateChange(
        val state: TextFieldState
    ) : AuthAction
    data class OnRegisterPasswordStateChange(
        val state: TextFieldState
    ) : AuthAction
    data class OnRegisterPasswordConfirmationStateChange(
        val state: TextFieldState
    ) : AuthAction
    data class OnRegisterPasswordVisibilityChange(
        val visible: Boolean
    ) : AuthAction
    data class OnRegisterPasswordConfirmationVisibilityChange(
        val visible: Boolean
    ) : AuthAction
    data object OnRegister : AuthAction

    // Personalization
    data class OnPersonalizationNameStateChange(
        val state: TextFieldState
    ) : AuthAction
    data class OnPersonalizationNameValueChange(
        val value: String
    ) : AuthAction
    data class OnPersonalizationDobValueChange(
        val value: Long
    ) : AuthAction
    data class OnPersonalizationDobStateChange(
        val state: TextFieldState
    ) : AuthAction
    data object OnSavePersonalization : AuthAction
}

sealed interface AuthEffect {
    data class OnShowToast(
        val message: String
    ) : AuthEffect
    data object OnNavigateToDashboard : AuthEffect
}