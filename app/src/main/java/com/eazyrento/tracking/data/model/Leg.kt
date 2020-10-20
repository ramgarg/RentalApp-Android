package com.eazyrento.tracking.data.model


import com.google.gson.annotations.SerializedName

data class Leg(
    @SerializedName("steps")
    val steps: List<Step>,
    @SerializedName("duration")
    val duration: Duration,
    @SerializedName("distance")
    val distance: Distance,
    @SerializedName("start_location")
    val startLocation: StartLocation,
    @SerializedName("end_location")
    val endLocation: EndLocation,
    @SerializedName("start_address")
    val startAddress: String,
    @SerializedName("end_address")
    val endAddress: String
)