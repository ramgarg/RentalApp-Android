package com.eazyrento.merchant.model.modelclass

data class ProductInfo(
    val attached_document: String,
    val availability_days: AvailabilityDays,
    val description: String,
    val document_name: String,
    val id: Int,
    val price: Double,
    val product_details: ProductDetails,
    val product_id: Int,
    val product_name: String,
    val quantity: Int,
    val variant: Any,
    val with_driver: Boolean,
    var monthly_price: Double,
    var daily_price: Double,
    var driver_cost_per_day: Double
)