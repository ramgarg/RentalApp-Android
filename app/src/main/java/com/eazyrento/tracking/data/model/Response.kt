package com.eazyrento.tracking.data.model


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("status")
    val status: String,
    @SerializedName("geocoded_waypoints")
    val geocodedWaypoints: List<GeocodedWaypoint>,
    @SerializedName("routes")
    val routes: List<Route>
)