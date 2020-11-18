package com.eazyrento.customer.dashboard.model.modelclass


import com.google.gson.annotations.SerializedName

data class OrderTrackingListItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("driver_info")
    val driverInfo: DriverInfo,
    @SerializedName("start_location")
    val startLocation: StartLocation,
    @SerializedName("end_location")
    val endLocation: EndLocation,
    @SerializedName("trip_status")
    val tripStatus: String
)