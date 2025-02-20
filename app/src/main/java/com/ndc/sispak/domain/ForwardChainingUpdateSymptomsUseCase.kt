package com.ndc.sispak.domain

import com.ndc.sispak.data.remote.body.forward_chaining.SymptomBody
import com.ndc.sispak.data.repository.system.forward_chaining.ForwardChainingRepository
import javax.inject.Inject

class ForwardChainingUpdateSymptomsUseCase @Inject constructor(
    private val forwardChainingRepository: ForwardChainingRepository
) {
    suspend operator fun invoke(
        systemId: Int,
        symptoms: List<SymptomBody>
    ) = forwardChainingRepository.updateSymptoms(systemId, symptoms)
}