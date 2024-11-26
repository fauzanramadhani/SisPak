package com.ndc.sispak.ui.component.textfield

sealed interface TextFieldState {
    data object Empty: TextFieldState
    data class Error(val errorMessage: String): TextFieldState
}