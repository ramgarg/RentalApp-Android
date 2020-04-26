package com.eazyrento.merchant.model.modelclass

data class MerchantAddProductReqModel(
    var attach_document: String,
    var availability_days: AvailabilityDays,
    var document_name: String,
    var price: Int,
    var product_id: Int,
    var quantity: Int,
    var with_driver: Boolean
){
    constructor():this("", AvailabilityDays(),"",0,-1,0,false)
}
