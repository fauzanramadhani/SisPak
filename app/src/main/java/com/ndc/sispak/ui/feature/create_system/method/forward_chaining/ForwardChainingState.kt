package com.ndc.sispak.ui.feature.create_system.method.forward_chaining

data class ForwardChainingState(
    val screen: Int = 0,
    val systemId: Int = 0,
    val bottomAppBarVisible: Boolean = true,
    val navigateButton: Boolean = true,
    val loadingBottomBar: Boolean = false,
    // update symptoms screen
    val loadingSymptoms: Boolean = true,
    val loadingSwipeSymptoms: Boolean = false,
    val errorLoadingSymptoms: String? = null,
    // update diseases screen
    val loadingDiseases: Boolean = true,
    val loadingSwipeDiseases: Boolean = false,
    val errorLoadingDiseases: String? = null,
)
