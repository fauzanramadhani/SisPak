package com.ndc.sispak.data.remote.service

import com.ndc.sispak.common.BaseResponse
import com.ndc.sispak.data.remote.response.MethodResponse
import retrofit2.Response
import retrofit2.http.GET

interface MethodService {
    @GET("/method")
    suspend fun getMethod(): Response<BaseResponse<List<MethodResponse>>>
}