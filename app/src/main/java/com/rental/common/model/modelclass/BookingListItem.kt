package com.rental.common.model.modelclass

data class BookingListItem(
    val agent_detail: AgentDetail,
    val customer_detail: CustomerDetailX,
    val end_date: String,
    val end_time: String,
    val id: Int,
    val order_id: String,
    val product_detail: ProductDetailX,
    val start_date: String,
    val start_time: String,
    val status: String,
    val with_driver: Boolean
)