package com.ndc.sispak.data.remote.response

import com.google.gson.annotations.SerializedName

data class SystemResponse(

	@field:SerializedName("createdAt")
	val createdAt: Long,

	@field:SerializedName("method")
	val method: MethodResponse,

	@field:SerializedName("createdBy")
	val createdBy: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("methodId")
	val methodId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String
)