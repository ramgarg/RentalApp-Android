package com.eazyrento.customer.payment.model.modelclass

data class PaymentListResModelItem(
    val added_on: String,
    val amount_paid: Double,
    val id: Int,
    val mode_of_payment: String,
    val order_id: String,
    val transaction_id: String,
    val status: String
)