package com.eazyrento.customer.dashboard.model.modelclass

import com.eazyrento.login.model.modelclass.AddressInfo

data class CustomerOrderDetailsResModel(
    val agent_detail: AgentDetail,
    val customer_detail: CustomerDetailX,
    val merchant_detail: List<MerchantDetail>,
    val order_amount_paid: Double,
    val order_id: String,
    val order_status: String,
    val pending_order_amount: Double,
    val product_detail: ProductDetailX,
    val total_order_amount: Double,
    val address_detail:AddressInfo
)