package com.ndc.sispak.ui.forward_chaining

import com.ndc.sispak.data.remote.response.forward_chaining.SymptomWithDiseaseResponse

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
    // update symptom with disease
    val symptomWithDisease: List<SymptomWithDiseaseResponse> = emptyList(),
    val loadingSymptomWithDisease: Boolean = true,
    val loadingSwipeSymptomWithDisease: Boolean = false,
    val errorLoadingSymptomWithDisease: String? = null,
)
