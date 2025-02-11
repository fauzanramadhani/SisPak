package com.ndc.sispak.domain

import com.ndc.sispak.data.repository.system.SystemRepository
import javax.inject.Inject

class CreateSystemUseCase @Inject constructor(
    private val systemRepository: SystemRepository
){
    suspend operator fun invoke(
        methodId: Int,
        title: String,
        description: String
    ) = systemRepository.createSystem(methodId, title, description)
}