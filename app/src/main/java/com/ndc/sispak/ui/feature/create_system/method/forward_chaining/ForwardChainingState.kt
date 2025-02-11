package com.ndc.sispak.ui.feature.create_system.method.forward_chaining

data class ForwardChainingState(
    val screen: Int = 0,
    val systemId: Int = 0,
    val bottomAppBarVisible: Boolean = false,
    val navigateButton: Boolean = true,
    val loadingBottomBar: Boolean = false,
)
