package com.eazyrento.customer.notification.model.repositry.API

import com.eazyrento.customer.notification.model.NotificationList
import com.eazyrento.customer.notification.model.NotificationModel
import com.eazyrento.webservice.PathURL
import retrofit2.Call
import retrofit2.http.GET

interface NotificationAPI {
    @GET(PathURL.NOTIFICATION)
    fun getNotificationList(): Call<NotificationList>
}