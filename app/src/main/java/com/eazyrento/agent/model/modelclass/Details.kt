package com.eazyrento.agent.model.modelclass

data class Details(
    val availability_days: AvailabilityDays,
    val distance: Double,
    val full_name: String,
    val merchant_id: Int,
    val mobile_number: String,
    val price: Double,
    var quantity_available: Int,
    var assign_quantity:Int,
    val profile_image:String
)