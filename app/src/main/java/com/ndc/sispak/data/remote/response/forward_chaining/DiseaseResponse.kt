package com.ndc.sispak.data.remote.response.forward_chaining

import com.google.gson.annotations.SerializedName

data class DiseaseResponse(

    @field:SerializedName("createdAt")
    val createdAt: Long,

    @field:SerializedName("systemId")
    val systemId: Int,

    @field:SerializedName("code")
    val code: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("id")
    val id: Int
)