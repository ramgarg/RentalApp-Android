package com.rental.common.model.modelclass

data class Booking(
    val customer_detail: CustomerDetail,
    val id: Int,
    val order_id: String,
    val product_detail: ProductDetail,
    val status: String
)