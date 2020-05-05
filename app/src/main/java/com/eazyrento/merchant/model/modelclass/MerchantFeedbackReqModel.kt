package com.eazyrento.merchant.model.modelclass

data class MerchantFeedbackReqModel(
    var order_id: String?,
    var agent_id: Int?,
    var customer_id: Int?,
    var review: String?,
    var rating: Double?

) {
        constructor():this("",-1,-1,"",0.0)
    }

