package com.ndc.sispak.data.remote.service

import com.ndc.sispak.common.BaseResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {
    @FormUrlEncoded
    @POST("/auth/basic")
    suspend fun authBasic(
        @Field("uid") uid: String,
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("dob") dob: Long,
    ): Response<BaseResponse<Unit>>

    @FormUrlEncoded
    @POST("/auth/google")
    suspend fun authWithGoogle(
        @Field("uid") uid: String,
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("dob") dob: Long,
    ): Response<BaseResponse<Unit>>
}