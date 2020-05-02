package com.eazyrento.common.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookingListItem(
    val agent_detail: AgentDetail,
    val customer_detail: CustomerDetail,
    val end_date: String,
    val end_time: String,
    val id: Int,
    val order_id: String,
    val product_detail: ProductDetail,
    val start_date: String,
    val start_time: String,
    val status: String,
    val with_driver: Boolean
) : Parcelable