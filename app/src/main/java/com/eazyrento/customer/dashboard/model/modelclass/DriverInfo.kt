package com.eazyrento.customer.dashboard.model.modelclass


import com.google.gson.annotations.SerializedName

data class DriverInfo(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("mobile_number")
    val mobileNumber: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("address_line")
    val addressLine: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("is_online")
    val isOnline: Boolean,
    @SerializedName("current_status")
    val currentStatus: Any,
    @SerializedName("profile_image")
    val profileImage: String
)