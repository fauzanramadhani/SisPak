package com.ndc.sispak.domain

import com.ndc.sispak.data.repository.UserRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke() = userRepository.getUserInfo()
}