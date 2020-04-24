package com.eazyrento.common.model.modelclass


data class Order_listing(val customer_detail: Customer_detail,
                         val product_detail: Product_detail,
                         val id: Int,
                         val order_id: String,
                         val status: String)