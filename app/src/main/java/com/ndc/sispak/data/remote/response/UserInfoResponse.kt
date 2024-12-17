package com.ndc.sispak.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(

	@field:SerializedName("uid")
	val uid: String,

	@field:SerializedName("createdAt")
	val createdAt: Long,

	@field:SerializedName("dob")
	val dob: Long,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String
)
