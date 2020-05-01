package com.eazyrento.common.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Booking(
    val customer_detail: CustomerDetail?=null,
    val product_detail: ProductDetail?=null,
    val agent_detail:AgentDetail?=null,
    val id: Int,
    val order_id: String,
    val status: String
) : Parcelable