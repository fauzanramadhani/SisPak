package com.ndc.sispak.data.remote.service

import com.ndc.sispak.common.BaseResponse
import com.ndc.sispak.data.remote.response.UserInfoResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("/user/info")
    suspend fun getUserInfo(): Response<BaseResponse<UserInfoResponse>>
}