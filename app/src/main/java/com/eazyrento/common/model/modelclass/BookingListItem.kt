package com.eazyrento.common.model.modelclass

import android.os.Parcelable
import com.eazyrento.customer.dashboard.model.modelclass.AgentDetail
import com.eazyrento.customer.dashboard.model.modelclass.CustomerDetailX
import com.eazyrento.customer.dashboard.model.modelclass.MerchantOrderDetail
import kotlinx.android.parcel.Parcelize

abstract class BaseBooking{
    abstract val customer_detail: CustomerDetailX?
    abstract val product_detail: ProductDetail?
    abstract val agent_detail:AgentDetail?
    abstract val id: Int
    abstract val order_id: String
    abstract val status: String
}

@Parcelize
data class BookingListItem(
   override val agent_detail: AgentDetail,
   override val customer_detail: CustomerDetailX,
   override val id: Int,
   override val order_id: String,
   override val product_detail: ProductDetail,
   override val status: String,

    val end_date: String,
    val end_time: String,
    val start_date: String,
    val start_time: String,
    val with_driver: Boolean,
    val is_category_vehicle:Boolean

) : Parcelable,BaseBooking()

@Parcelize
data class Booking(
    override val customer_detail: CustomerDetailX?=null,
    override val product_detail: ProductDetail?=null,
    override val agent_detail:AgentDetail?=null,
    override val id: Int,
    override val order_id: String,
    override val status: String,
    val merchant_order_detail: MerchantOrderDetail?,
    val start_date: String?
) : Parcelable,BaseBooking()