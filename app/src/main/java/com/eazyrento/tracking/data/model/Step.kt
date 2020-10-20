package com.eazyrento.tracking.data.model


import com.google.gson.annotations.SerializedName

data class Step(
    @SerializedName("travel_mode")
    val travelMode: String,
    @SerializedName("start_location")
    val startLocation: StartLocation,
    @SerializedName("end_location")
    val endLocation: EndLocation,
    @SerializedName("polyline")
    val polyline: Polyline,
    @SerializedName("duration")
    val duration: Duration,
    @SerializedName("html_instructions")
    val htmlInstructions: String,
    @SerializedName("distance")
    val distance: Distance
)