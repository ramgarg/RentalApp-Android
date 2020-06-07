package com.eazyrento.customer.dashboard.model.modelclass

import com.eazyrento.login.model.modelclass.AddressInfo

data class OrderDetailsResModel(
    var agent_detail: AgentDetail,
    var customer_detail: CustomerDetailX,
    var merchant_detail: List<MerchantDetail>,
    var order_amount_paid: Double,
    var order_id: String,
    var order_status: String,
    var pending_order_amount: Double,
    var product_detail: ProductDetailX,
    var total_order_amount: Double,
    var address_detail:AddressInfo
)