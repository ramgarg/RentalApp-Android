package com.rental.customer.dashboard.model.modelclass

class OrderSummaryResponseModel
    (val order_id: Int,
     val price: Int,
     val pendingAmount: Double,
     val quantity: Int,
     val withDriver: Boolean,
     val startDate:String,
     val startTime:String,
     val endDate:String,
     val endTime:String,
     val location:String){
}