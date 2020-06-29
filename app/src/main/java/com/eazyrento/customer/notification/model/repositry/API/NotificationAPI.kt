package com.eazyrento.customer.notification.model.repositry.API

import com.eazyrento.customer.notification.model.NotificationDeleteModel
import com.eazyrento.customer.notification.model.NotificationList
import com.eazyrento.customer.notification.model.NotificationModel
import com.eazyrento.customer.notification.model.NotificationReadModel
import com.eazyrento.webservice.PathURL
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface NotificationAPI {
    // LIST
    @GET(PathURL.NOTIFICATION)
    fun getNotificationList(): Call<NotificationList>

    // DELETE
   // @DELETE(PathURL.DELETE_NOTIFICATION)

    @HTTP(method = "DELETE", path = PathURL.DELETE_NOTIFICATION, hasBody = true)
    fun deleteNotification(@Body deleteModel: NotificationDeleteModel): Call<JsonElement>

    // READ
    @POST(PathURL.READ_NOTIFICATION)
    fun readNotification(@Body readModel: NotificationReadModel): Call<JsonElement>

}