package com.eazyrento.customer.notification.model


abstract class BaseNoticationModel {
    abstract val deep_link: String?
    abstract val id: Int
    abstract val obj_id:String
}

data class NotificationModel(

    override val deep_link: String?,
    override val id: Int,
    override val obj_id: String,
    val sent_message:String,
    val requested_amount:Double,
    var is_read:Boolean,
    val added_on:String

) : BaseNoticationModel()

class NotificationList:ArrayList<NotificationModel>()

abstract class BaseNotificationOpration{abstract val notification_id:Int?}

data class NotificationDeleteModel(override val notification_id:Int?,val delete_option:String):BaseNotificationOpration()
data class NotificationReadModel(override val notification_id:Int,val read_option:String):BaseNotificationOpration()

