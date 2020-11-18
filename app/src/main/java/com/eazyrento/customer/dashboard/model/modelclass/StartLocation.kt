package com.eazyrento.customer.dashboard.model.modelclass


import com.google.gson.annotations.SerializedName

data class StartLocation(
    @SerializedName("id")
    val id: Int,
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("appartment")
    val appartment: Any,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("address_line")
    val addressLine: String,
    @SerializedName("address_type")
    val addressType: String
)