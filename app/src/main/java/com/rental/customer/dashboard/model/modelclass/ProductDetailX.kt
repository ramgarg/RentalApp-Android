package com.rental.customer.dashboard.model.modelclass

data class ProductDetailX(
    val end_date: String,
    val end_time: String,
    val product_info: ProductInfo,
    val product_name: String,
    val quantity: Int,
    val start_date: String,
    val start_time: String,
    val starting_price: Double,
    val with_driver: Boolean,
    val work_location: String
)