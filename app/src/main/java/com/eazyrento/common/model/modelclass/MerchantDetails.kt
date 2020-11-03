package com.eazyrento.common.model.modelclass


import com.google.gson.annotations.SerializedName

data class MerchantDetails(
    @SerializedName("id")
    val id: Int,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("mobile_number")
    val mobileNumber: String,
    @SerializedName("profile_image")
    val profileImage: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("quantity_available")
    val quantityAvailable: Int
)