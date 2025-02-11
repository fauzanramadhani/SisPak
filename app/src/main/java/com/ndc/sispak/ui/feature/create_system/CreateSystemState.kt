package com.ndc.sispak.ui.feature.create_system

import com.ndc.sispak.R
import com.ndc.sispak.data.remote.response.MethodResponse
import com.ndc.sispak.data.remote.response.MethodStepResponse
import com.ndc.sispak.ui.component.textfield.TextFieldState

data class CreateSystemState(
    val screen: Int = 0,
    val navigateButton: Boolean = true,
    val loading: Boolean = false,
    val errorLoadMethod: String = "",
    val searchValue: String = "",
    val method: List<MethodResponse> = listOf(),
    val methodVisible: List<MethodResponse> = listOf(),
    val bottomAppBarVisible: Boolean = false,
    val bottomAppBarTitle: Int = R.string.next,
    val selectedMethod: MethodResponse? = null,
    val selectedMethodStep: List<MethodStepResponse>? = null,
    // Create Name Screen
    val createNameTitleValue: String = "",
    val createNameTitleState: TextFieldState = TextFieldState.Empty,
    val createNameSummaryValue: String = "",
    val loadingSaveName: Boolean = false,
)