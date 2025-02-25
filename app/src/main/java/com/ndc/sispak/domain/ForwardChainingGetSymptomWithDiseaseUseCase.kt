package com.ndc.sispak.domain

import com.ndc.sispak.data.repository.system.forward_chaining.ForwardChainingRepository
import javax.inject.Inject

class ForwardChainingGetSymptomWithDiseaseUseCase @Inject constructor(
    private val forwardChainingRepository: ForwardChainingRepository
) {
    suspend operator fun invoke(
        systemId: Int
    ) = forwardChainingRepository.getSymptomWithDisease(systemId)
}