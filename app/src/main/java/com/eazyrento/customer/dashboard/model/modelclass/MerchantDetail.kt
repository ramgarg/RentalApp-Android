package com.eazyrento.customer.dashboard.model.modelclass

data class MerchantDetail(
    var amount: Int,
    var full_name: String,
    var merchant_id: Int,
    var mobile_number: String,
    var price: Int,
    var quantity: Int
)