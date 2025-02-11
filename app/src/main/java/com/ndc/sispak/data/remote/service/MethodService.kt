package com.ndc.sispak.data.remote.service

import com.ndc.sispak.common.BaseResponse
import com.ndc.sispak.data.remote.response.MethodResponse
import com.ndc.sispak.data.remote.response.MethodStepResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MethodService {
    @GET("/method")
    suspend fun getMethod(): Response<BaseResponse<List<MethodResponse>>>

    @GET("/method/step")
    suspend fun getStep(
        @Query("method_id") methodId: Int
    ): Response<BaseResponse<List<MethodStepResponse>>>
}