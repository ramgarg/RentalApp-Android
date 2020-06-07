package com.eazyrento.customer.dashboard.model.modelclass

import com.eazyrento.Constant

abstract class BaseUserRoleDetail(val user_id: Int){
    abstract val full_name: String
    abstract val userRole:String?
    abstract val mobile_number: String
    abstract val profile_image: String
}

data class MerchantDetail(

    override val full_name: String,
    override var userRole:String?,
    override val mobile_number: String,
    override val profile_image: String,

    val merchant_id: Int,

    var amount: Double,
    var price: Double,
    var quantity: Int

):BaseUserRoleDetail(merchant_id)

data class CustomerDetailX(

    override val full_name: String,
    override var userRole:String?,
    override val mobile_number: String,
    override val profile_image: String,

    val id: Int
):BaseUserRoleDetail(id)

data class AgentDetail(

    override val full_name: String,
    override var userRole:String?,
    override val mobile_number: String,
    override val profile_image: String,

    val id: Int
):BaseUserRoleDetail(id)