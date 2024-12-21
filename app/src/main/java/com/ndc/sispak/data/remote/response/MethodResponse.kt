package com.ndc.sispak.data.remote.response

import com.google.gson.annotations.SerializedName

data class MethodResponse(

	@field:SerializedName("createdAt")
	val createdAt: Long,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String
)
