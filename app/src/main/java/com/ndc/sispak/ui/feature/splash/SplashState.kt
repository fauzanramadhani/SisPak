package com.ndc.sispak.ui.feature.splash

import kotlinx.serialization.Serializable

@Serializable
data class SplashState(
    val empty: String = "",
    val reloadIconVisibility: Boolean = false,
)