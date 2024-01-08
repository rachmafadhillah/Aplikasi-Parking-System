package com.example.iotsystemparking.model

import com.google.gson.annotations.SerializedName

data class ModelPendapatan(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("jenis")
	val jenis: String? = null,

	@field:SerializedName("total_harga")
	val totalHarga: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null
)
