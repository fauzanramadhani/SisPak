package com.ndc.sispak.data.repository.system

import com.ndc.sispak.common.apiFlow
import com.ndc.sispak.data.remote.service.system.SystemService
import javax.inject.Inject

class SystemRepository @Inject constructor(
    private val systemService: SystemService
) {
    suspend fun createSystem(
        methodId: Int,
        title: String,
        description: String
    ) = apiFlow { systemService.createSystem(methodId, title, description) }

    suspend fun getAllSystem() = apiFlow { systemService.getAllSystem() }

    suspend fun getSystemById(systemId: Int) = apiFlow { systemService.getSystemById(systemId) }
}