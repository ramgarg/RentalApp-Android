package com.eazyrento.customer.notification.model


abstract class BaseNoticationModel {
    abstract val deep_link: String?
    abstract val id: Long
    abstract val obj_id:String
}

data class NotificationModel(

    override val deep_link: String?,
    override val id: Long,
    override val obj_id: String,
    val sent_message:String,
    val requested_amount:Double,
    val is_read:Boolean,
    val added_on:String

) : BaseNoticationModel()

class NotificationList:ArrayList<NotificationModel>()
