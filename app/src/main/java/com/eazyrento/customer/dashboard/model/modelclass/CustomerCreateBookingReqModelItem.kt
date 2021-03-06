package com.eazyrento.customer.dashboard.model.modelclass

import com.eazyrento.common.model.modelclass.ProductDetailsResModel
import com.eazyrento.login.model.modelclass.AddressInfo

data class CustomerCreateBookingReqModelItem(
    var address: AddressInfo?,
    var address_id: Int?,
    var start_date: String,
    var start_time: String,
    var end_date: String,
    var end_time: String,
    var product_id: Int,
    var quantity: Int,

    var with_driver: Boolean,

    // it is only for showing data
    var projectDetails: ProductDetailsResModel?,
    var booking_days:Long,
    var bookingTimeInMin:Long?

){
    constructor():this(null,-1,"","","","",-1,0,false,null,-1,null)
}