package com.ndc.sispak.domain

import android.content.Intent
import com.ndc.sispak.data.repository.AuthRepository
import javax.inject.Inject

class HandleLoginWithGoogleUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(intent: Intent) = authRepository.handleSignInWithGoogle(intent)
}