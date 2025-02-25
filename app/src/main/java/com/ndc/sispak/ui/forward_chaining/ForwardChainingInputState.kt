package com.ndc.sispak.ui.forward_chaining

import com.ndc.sispak.ui.component.textfield.TextFieldState

data class ForwardChainingInputState(
    val id: Int? = null,
    val code: String = "",
    val value: String = "",
    val stateCode: TextFieldState = TextFieldState.Empty,
    val stateValue: TextFieldState = TextFieldState.Empty,
)