package com.ndc.sispak.domain

import com.ndc.sispak.data.repository.system.SystemRepository
import javax.inject.Inject

class GetSystemByIdUseCase @Inject constructor(
    private val systemRepository: SystemRepository
) {
    suspend operator fun invoke(systemId: Int) =
        systemRepository.getSystemById(systemId)
}