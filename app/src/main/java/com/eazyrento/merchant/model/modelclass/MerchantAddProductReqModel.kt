package com.eazyrento.merchant.model.modelclass

data class MerchantAddProductReqModel(

    var document_name: String,
    internal var price: Double,
    var product_id: Int,
    var quantity: Int,
    var with_driver: Boolean,
    var variant:String,
    var availability_days: AvailabilityDays,
    var attach_document: String?,
    var monthly_price: Double,
    var daily_price: Double,
    var driver_cost_per_day: Double

){
    constructor():this("", 0.0,-1,0,false,"",AvailabilityDays(),null,0.0,0.0,0.0)
}
