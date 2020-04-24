package com.eazyrento.agent.model.modelclass

data class AgentDashboardResModel(
    val bookings: List<Booking>,
    val completed_orders_count: Int,
    val in_progress_orders_count: Int,
    val rejected_orders_count: Int,
    val status: Int
)