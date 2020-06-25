package com.eazyrento.agent.model.modelclass

data class Merchant(
    var amount: Double,
    val merchant_id: Int,
    val price: Double,
    var quantity: Int
)