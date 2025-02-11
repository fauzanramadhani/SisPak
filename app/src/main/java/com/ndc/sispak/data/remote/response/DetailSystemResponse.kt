package com.ndc.sispak.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailSystemResponse(

	@field:SerializedName("createdAt")
	val createdAt: Long,

	@field:SerializedName("method")
	val method: Method,

	@field:SerializedName("createdBy")
	val createdBy: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("methodId")
	val methodId: Int,

	@field:SerializedName("diseases")
	val diseases: List<Any>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String
)

data class Method(

	@field:SerializedName("summary")
	val summary: String,

	@field:SerializedName("createdAt")
	val createdAt: Long,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String
)
