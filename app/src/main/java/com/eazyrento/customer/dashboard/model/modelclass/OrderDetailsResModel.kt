package com.eazyrento.customer.dashboard.model.modelclass

import android.os.Parcelable
import com.eazyrento.login.model.modelclass.AddressInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
open class OrderDetailsResModel(

    var id: Int,
    var order_id: String,
    var order_status: String?,
    var status: String,

    var agent_detail: AgentDetail?,
    var customer_detail: CustomerDetailX?,
    var merchant_detail: List<MerchantDetail>?,
    //sub order only for agent
    var sub_orders: List<Int>?,

    var address_detail: AddressInfo?,
    var product_detail: ProductDetailX?,

    val merchant_order_detail: MerchantProductDetails?,

    var amount_pending_for_approval: Double,
    var pending_order_amount: Double,
    var order_amount_with_commission: Double,
    var amount_to_pay: Double,

    var tip_amount: Double,
    var total_order_amount: Double,
    var order_amount_paid: Double,
    var is_category_vehicle: Boolean,
    var is_driver_assigned: Boolean

) : Parcelable

// sub order
@Parcelize
data class SubOrderReqResModel(
    val id: Int,
    val order_id: String,
    var merchant_detail: MerchantDetail?,
    var product_detail: ProductDetailX?,
    var amount: Double,
    var quantity: Int,
    var price: Double,
    var order_status: String,
    var is_driver_assigned: Boolean
) : Parcelable

data class SubOrderUpdateReqModel(
 
    val price: Double,
    val quantity: Int,
    val status: String
    
)