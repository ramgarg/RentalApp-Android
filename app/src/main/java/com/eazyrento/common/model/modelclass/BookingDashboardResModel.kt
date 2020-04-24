package com.eazyrento.common.model.modelclass

data class BookingDashboardResModel(
    val bookings: List<Booking>,
    val completed_orders_count: Int,
    val in_progress_orders_count: Int,
    val rejected_orders_count: Int,
    val status: Int
)