package com.ndc.sispak.domain

import com.ndc.sispak.data.repository.AuthRepository
import javax.inject.Inject

class LoginBasicUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, password: String) = authRepository.loginBasic(email, password)
}