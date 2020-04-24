package com.eazyrento.agent.model.modelclass

data class Booking(
    val agent_detail: AgentDetail,
    val customer_detail: CustomerDetail,
    val id: Int,
    val order_id: String,
    val product_detail: ProductDetail,
    val status: String
)