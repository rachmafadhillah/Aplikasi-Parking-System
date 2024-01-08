package com.example.iotsystemparking.model

import com.google.gson.annotations.SerializedName

data class ModelListGempa(

	@field:SerializedName("Infogempa")
	val infogempa: InfogempaList? = null
)

data class InfogempaList(

	@field:SerializedName("gempa")
	val gempa: List<GempaItem?>? = null
)

data class GempaItem(

	@field:SerializedName("Wilayah")
	val wilayah: String? = null,

	@field:SerializedName("Kedalaman")
	val kedalaman: String? = null,

	@field:SerializedName("Jam")
	val jam: String? = null,

	@field:SerializedName("Coordinates")
	val coordinates: String? = null,

	@field:SerializedName("Potensi")
	val potensi: String? = null,

	@field:SerializedName("Tanggal")
	val tanggal: String? = null,

	@field:SerializedName("Bujur")
	val bujur: String? = null,

	@field:SerializedName("Magnitude")
	val magnitude: String? = null,

	@field:SerializedName("Lintang")
	val lintang: String? = null,

	@field:SerializedName("DateTime")
	val dateTime: String? = null
)
