package com.ndc.sispak.data.remote.body.forward_chaining

import com.google.gson.annotations.SerializedName

data class DiseaseBody(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("code")
    val code: String,

    @field:SerializedName("description")
    val description: String
)
