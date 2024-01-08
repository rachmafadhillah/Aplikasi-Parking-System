package com.example.iotsystemparking.model

import com.google.gson.annotations.SerializedName

data class ModelAverage(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data1? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data1(

	@field:SerializedName("Senin")
	val senin: Int? = null,

	@field:SerializedName("Selasa")
	val selasa: Int? = null,

	@field:SerializedName("Minggu")
	val minggu: Int? = null,

	@field:SerializedName("Rabu")
	val rabu: Int? = null,

	@field:SerializedName("Sabtu")
	val sabtu: Int? = null,

	@field:SerializedName("Jumat")
	val jumat: Int? = null,

	@field:SerializedName("Kamis")
	val kamis: Int? = null
)
