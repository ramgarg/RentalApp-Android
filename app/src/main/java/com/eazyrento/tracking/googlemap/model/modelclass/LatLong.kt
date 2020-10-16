package com.eazyrento.tracking.googlemap.model.modelclass


import com.google.gson.annotations.SerializedName

data class LatLong(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)