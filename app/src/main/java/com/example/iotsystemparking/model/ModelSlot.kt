package com.example.iotsystemparking.model

import com.google.gson.annotations.SerializedName

data class ModelSlot(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("totalKendaraan")
	val totalKendaraan: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItem(

	@field:SerializedName("waktu_masuk")
	val waktuMasuk: String? = null,

	@field:SerializedName("waktu_keluar")
	val waktuKeluar: Any? = null,

	@field:SerializedName("lantai")
	val lantai: String? = null,

	@field:SerializedName("jenis")
	val jenis: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
