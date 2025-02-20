package com.ndc.sispak.domain

import com.ndc.sispak.data.repository.system.forward_chaining.ForwardChainingRepository
import javax.inject.Inject

class ForwardChainingGetSymptomsUseCase @Inject constructor(
    private val forwardChainingRepository: ForwardChainingRepository
) {
    suspend operator fun invoke(
        systemId: Int
    ) = forwardChainingRepository.getSymptoms(systemId)
}