package com.ndc.sispak.data.repository.system.forward_chaining

import com.ndc.sispak.common.apiFlow
import com.ndc.sispak.data.remote.body.forward_chaining.SymptomBody
import com.ndc.sispak.data.remote.service.system.forward_chaining.ForwardChainingService
import javax.inject.Inject

class ForwardChainingRepository @Inject constructor(
    private val forwardChainingService: ForwardChainingService
) {
    suspend fun updateSymptoms(
        systemId: Int,
        symptoms: List<SymptomBody>
    ) = apiFlow { forwardChainingService.updateSymptoms(systemId, symptoms) }

    suspend fun getSymptoms(
        systemId: Int,
    ) = apiFlow { forwardChainingService.getSymptoms(systemId) }
}