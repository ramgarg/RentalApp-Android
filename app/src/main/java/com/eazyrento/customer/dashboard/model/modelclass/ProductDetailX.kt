package com.eazyrento.customer.dashboard.model.modelclass

data class ProductDetailX(
    var end_date: String,
    var end_time: String,
    val product_info: ProductInfo,
    val product_name: String,
    val quantity: Int,
    var start_date: String,
    var start_time: String,
    val starting_price: Double,
    var with_driver: Boolean,
    val work_location: String
)