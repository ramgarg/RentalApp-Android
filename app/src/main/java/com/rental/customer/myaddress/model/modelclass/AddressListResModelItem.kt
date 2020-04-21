package com.rental.customer.myaddress.model.modelclass

data class AddressListResModelItem(
    val address_line: String,
    val address_type: String,
    val appartment: String,
    val city: String,
    val country: String,
    val id: Int,
    val is_default: Boolean,
    val latitude: Double,
    val longitude: Double,
    val state: String
)