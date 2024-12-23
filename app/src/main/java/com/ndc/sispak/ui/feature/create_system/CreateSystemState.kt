package com.ndc.sispak.ui.feature.create_system

import com.ndc.sispak.data.remote.response.MethodResponse

data class CreateSystemState(
    val screen: Int = 0,
    val backButtonEnabled: Boolean = true,
    val loading: Boolean = false,
    val errorLoadMethod: String = "",
    val searchValue: String = "",
    val method: List<MethodResponse> = listOf(),
    val methodVisible: List<MethodResponse> = listOf(),
)