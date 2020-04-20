package com.rental.merchant.model.modelclass

data class MerchantDashboardResModel(
    val bookings: List<Booking>,
    val completed_orders_count: Int,
    val in_progress_orders_count: Int,
    val rejected_orders_count: Int,
    val status: Int
)