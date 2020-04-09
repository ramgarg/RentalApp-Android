package com.rental.customer.dashboard.model.modelclass

data class CustomerOrderListResModelItem(
    val customer_detail: CustomerDetail,
    val id: Int,
    val order_id: String,
    val product_detail: ProductDetail,
    val status: String
)