package com.eazyrento.common.model.modelclass


import com.google.gson.annotations.SerializedName

data class MerchantAssignDriver(
    @SerializedName("order_id")
    val orderId: String,
    @SerializedName("sub_order_id")
    val subOrderId: Int,
    @SerializedName("driver_id")
    val driverId: Int
)