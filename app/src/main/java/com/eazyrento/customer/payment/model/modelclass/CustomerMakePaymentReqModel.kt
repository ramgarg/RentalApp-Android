package com.eazyrento.customer.payment.model.modelclass


abstract class BaseMakePaymentModel {
    abstract var order_id: String

}

data class CustomerMakePaymentReqModel(
    override var order_id: String,
    var amount_paid: Double,
    var is_tip: Boolean,
    var mode_of_payment: String,

    var status: String?,
    var transaction_id: String

) : BaseMakePaymentModel()

data class AgentMakePaymentReqModel(
    override var order_id: String, var requested_amount: Double
) : BaseMakePaymentModel()

// payment Getway
data class PaymentGetwayCheckoutIDReqModel(
    var amount_paid: Double
)

data class PaymentGetwayCheckoutIDResModel(
    val status: Int,
    val checkout_id:String,
    val order_id:Int,
    val user_id:Int,
    val trnx_id:String
)

