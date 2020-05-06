package com.eazyrento.login.model.modelclass

data class AddressInfo(
    var address_line: String,
    var address_type: String,
    var appartment: String,
    var city: String,
    var country: String,
    var id: Int,
    var is_default: Boolean,
    var latitude: Double,
    var longitude: Double,
    var state: String
)