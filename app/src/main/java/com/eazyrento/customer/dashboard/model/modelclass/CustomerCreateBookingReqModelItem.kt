package com.eazyrento.customer.dashboard.model.modelclass

import com.eazyrento.common.model.modelclass.ProductDetailsResModel

data class CustomerCreateBookingReqModelItem(
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
    var booking_days:Long
){
    constructor():this(-1,"","","","",-1,0,false,null,-1)
}