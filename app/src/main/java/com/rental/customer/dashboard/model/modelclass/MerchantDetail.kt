package com.rental.customer.dashboard.model.modelclass

data class MerchantDetail(
    val amount: Int,
    val full_name: String,
    val merchant_id: Int,
    val mobile_number: String,
    val price: Int,
    val quantity: Int
)