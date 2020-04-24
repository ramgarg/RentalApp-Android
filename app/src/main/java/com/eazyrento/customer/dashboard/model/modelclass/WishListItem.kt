package com.eazyrento.customer.dashboard.model.modelclass

data class WishListItem(
    val added_on: String,
    val id: Int,
    val price: Double,
    val product_id: Int,
    val product_name: String
)