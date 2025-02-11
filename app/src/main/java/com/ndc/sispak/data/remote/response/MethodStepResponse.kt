package com.ndc.sispak.data.remote.response

import com.google.gson.annotations.SerializedName

data class MethodStepResponse(

	@field:SerializedName("methodId")
	val methodId: Int,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("stepNumber")
	val stepNumber: Int,

	@field:SerializedName("title")
	val title: String
)
