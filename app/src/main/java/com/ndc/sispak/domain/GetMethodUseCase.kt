package com.ndc.sispak.domain

import com.ndc.sispak.data.repository.MethodRepository
import javax.inject.Inject

class GetMethodUseCase @Inject constructor(
    private val methodRepository: MethodRepository
) {
    suspend operator fun invoke() = methodRepository.getMethod()
}