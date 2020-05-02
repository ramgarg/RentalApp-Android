package com.eazyrento.agent.model.modelclass

data class AssignMerchantsReqModel(
    var booking_id: Int=0,
    var merchant_list: ArrayList<Merchant> = ArrayList(),
    var order_id: String =""
)