package com.eazyrento.common.model.modelclass


import com.google.gson.annotations.SerializedName

data class DriverList(
    @SerializedName("status")
    val status: Int,
    @SerializedName("drivers_list")
    val driversList: List<Drivers>
)