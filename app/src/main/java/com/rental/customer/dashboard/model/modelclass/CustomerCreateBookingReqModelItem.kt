package com.rental.customer.dashboard.model.modelclass

data class CustomerCreateBookingReqModelItem(
    val address_id: Int,
    val end_date: String,
    val end_time: String,
    val product_id: Int,
    val quantity: Int,
    val start_date: String,
    val start_time: String,
    val with_driver: Boolean
)