package com.eazyrento.customer.dashboard.model.modelclass



data class CustomerOrderListResModelItem(
    val customer_detail: CustomerDetailX?,
    val id: Int,
    val order_id: String,
    val product_detail: ProductDetail?,
    val status: String,
    val merchant_order_detail:MerchantProductDetails?,
    val agent_detail: AgentDetail?

)