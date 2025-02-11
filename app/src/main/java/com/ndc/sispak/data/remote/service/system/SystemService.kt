package com.ndc.sispak.data.remote.service.system

import com.ndc.sispak.common.BaseResponse
import com.ndc.sispak.data.remote.response.DetailSystemResponse
import com.ndc.sispak.data.remote.response.SystemResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SystemService {

    @GET("/system")
    suspend fun getAllSystem(): Response<BaseResponse<List<SystemResponse>>>

    @GET("/system/get_system_by_id")
    suspend fun getSystemById(
        @Query("system_id") systemId: Int,
    ): Response<BaseResponse<DetailSystemResponse>>

    @FormUrlEncoded
    @POST("/system/create_system")
    suspend fun createSystem(
        @Query("method_id") methodId: Int,
        @Field("title") title: String,
        @Field("description") description: String
    ): Response<BaseResponse<Int>>
}