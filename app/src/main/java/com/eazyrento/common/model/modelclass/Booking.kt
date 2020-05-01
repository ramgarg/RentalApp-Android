package com.eazyrento.common.model.modelclass

data class Booking(
    val customer_detail: CustomerDetail,
    val product_detail: ProductDetail,
    val agent_detail:AgentDetail,
    val id: Int,
    val order_id: String,
    val status: String
)