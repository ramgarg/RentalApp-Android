package com.eazyrento.tracking.data.model


import com.google.gson.annotations.SerializedName

data class Route(
    @SerializedName("summary")
    val summary: String,
    @SerializedName("legs")
    val legs: List<Leg>,
    @SerializedName("copyrights")
    val copyrights: String,
    @SerializedName("overview_polyline")
    val overviewPolyline: OverviewPolyline,
    @SerializedName("warnings")
    val warnings: List<Any>,
    @SerializedName("waypoint_order")
    val waypointOrder: List<Int>,
    @SerializedName("bounds")
    val bounds: Bounds
)