package com.emha.cariuniversitas.data.model

import com.google.gson.annotations.SerializedName

data class UniversityResponse(
    @SerializedName("id_sp") val idSp: String,
    val university: String,
    @SerializedName("singkatan") val abbreviation: String,
    @SerializedName("alamat") val address: String
)
