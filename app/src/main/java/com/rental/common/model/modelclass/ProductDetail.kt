package com.rental.merchant.model.modelclass

data class ProductDetail(
    val product_name: String,
    val quantity: Int,
    val start_date: String,
    val starting_price: Double
)