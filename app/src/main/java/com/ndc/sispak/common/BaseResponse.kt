package com.ndc.sispak.common

import com.google.gson.annotations.SerializedName

data class BaseResponse<out T>(

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("data")
    val data: T? = null
)