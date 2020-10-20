package com.eazyrento.tracking.data.model


import com.google.gson.annotations.SerializedName

data class LatLong(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)