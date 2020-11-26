package com.eazyrento.common.model.modelclass


import com.google.gson.annotations.SerializedName

data class ApplyPromoCodeResModel(
    @SerializedName("total_price")
    val totalPrice: Double,
    @SerializedName("amount_paid")
    val amountPaid: Double,
    @SerializedName("amount_to_pay")
    val amountToPay: Double,
    @SerializedName("amount_pending_for_approval")
    val amountPendingForApproval: Double
)