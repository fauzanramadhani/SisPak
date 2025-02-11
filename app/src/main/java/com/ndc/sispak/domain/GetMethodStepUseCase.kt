package com.ndc.sispak.domain

import com.ndc.sispak.data.repository.MethodRepository
import javax.inject.Inject

class GetMethodStepUseCase @Inject constructor(
    private val methodRepository: MethodRepository
) {
    suspend operator fun invoke(methodId: Int) = methodRepository.getMethodStep(methodId)
}