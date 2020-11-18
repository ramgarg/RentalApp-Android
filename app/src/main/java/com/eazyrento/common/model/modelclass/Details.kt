package com.eazyrento.common.model.modelclass


import com.google.gson.annotations.SerializedName

data class Details(
    @SerializedName("merchant_details")
    val merchantDetails: MerchantDetails,
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("driver_id")
    val driverId: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("mobile_number")
    val mobileNumber: String,
    @SerializedName("profile_image")
    val profileImage: String

)