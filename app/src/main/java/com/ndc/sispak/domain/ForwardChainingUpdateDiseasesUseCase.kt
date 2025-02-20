package com.ndc.sispak.domain

import com.ndc.sispak.data.remote.body.forward_chaining.DiseaseBody
import com.ndc.sispak.data.repository.system.forward_chaining.ForwardChainingRepository
import javax.inject.Inject

class ForwardChainingUpdateDiseasesUseCase @Inject constructor(
    private val forwardChainingRepository: ForwardChainingRepository
) {
    suspend operator fun invoke(
        systemId: Int,
        diseases: List<DiseaseBody>
    ) = forwardChainingRepository.updateDiseases(systemId, diseases)
}