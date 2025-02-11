package com.ndc.sispak.domain

import com.ndc.sispak.data.repository.system.SystemRepository
import javax.inject.Inject

class GetAllSystemUseCase @Inject constructor(
    private val systemRepository: SystemRepository,
) {
    suspend operator fun invoke() = systemRepository.getAllSystem()
}