package com.eazyrento.customer.myaddress.model.modelclass

data class AddressCreateReqModelItem (
    var address_line: String?,
    var address_type: String?,
    var appartment: String?,
    var city: String?,
    var country: String?,
    var is_default: Boolean?,
    var latitude: Double?,
    var longitude: Double?,
    var state: String?
){
    constructor():this("","","",
        "","",false,0.0,0.0,"")
}

