package com.eazyrento.customer.dashboard.model.modelclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

abstract class BaseUserRoleDetail(var user_id: Int){
    abstract val full_name: String
    abstract var userRole:String?
    abstract val mobile_number: String
    abstract val profile_image: String
}
@Parcelize
data class MerchantDetail(

    override val full_name: String,
    override var userRole:String?,
    override val mobile_number: String,
    override val profile_image: String,

    val merchant_id: Int,

    var amount: Double,
    var price: Double,
    var quantity: Int

):BaseUserRoleDetail(merchant_id), Parcelable
@Parcelize
data class CustomerDetailX(

    override val full_name: String,
    override var userRole:String?,
    override val mobile_number: String,
    override val profile_image: String,

    val id: Int
):BaseUserRoleDetail(id), Parcelable

@Parcelize
data class AgentDetail(

    override val full_name: String,
    override var userRole:String?,
    override val mobile_number: String,
    override val profile_image: String,

    val id: Int
):BaseUserRoleDetail(id), Parcelable

@Parcelize
data class MerchantOrderDetail(
 val product_name:String,
 val booking_price:String,
 val merchant_quantity:Int

):Parcelable