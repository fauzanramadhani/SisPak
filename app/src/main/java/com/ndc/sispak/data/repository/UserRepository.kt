package com.ndc.sispak.data.repository

import com.ndc.sispak.common.apiFlow
import com.ndc.sispak.data.remote.service.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService
) {
    fun getUserInfo() = apiFlow {
        userService.getUserInfo()
    }
}