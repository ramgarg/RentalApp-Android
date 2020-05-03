package com.eazyrento.customer.myaddress.model.modelclass

data class AddressCreateReqModelItem (
    var address_type: String?,
    var appartment: String?,
    var address_line: String?,
    var latitude: Double?,
    var longitude: Double?,
    var city: String?,
    var state: String?,
    var country: String?,
    var is_default: Boolean?


){
    constructor():this("","","",
        0.0,0.0,"","","",false)
}


