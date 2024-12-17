package com.ndc.sispak.domain

import com.ndc.sispak.data.repository.AuthRepository
import javax.inject.Inject

class ServiceAuthWithGoogleUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        name: String,
        dob: Long
    ) = authRepository.serviceAuthWithGoogle(name, dob)
}