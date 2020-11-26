package com.eazyrento.common.model.modelclass


import com.google.gson.annotations.SerializedName

data class ApplyPromoCodeReqModel(
    @SerializedName("order_id")
    val orderId: String,
    @SerializedName("code")
    val code: String
)