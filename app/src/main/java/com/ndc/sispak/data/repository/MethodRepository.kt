package com.ndc.sispak.data.repository

import com.ndc.sispak.common.apiFlow
import com.ndc.sispak.data.remote.service.MethodService
import javax.inject.Inject

class MethodRepository @Inject constructor(
    private val methodService: MethodService
) {
    suspend fun getMethod() = apiFlow { methodService.getMethod() }

    suspend fun getMethodStep(methodId: Int) = apiFlow { methodService.getStep(methodId) }
}