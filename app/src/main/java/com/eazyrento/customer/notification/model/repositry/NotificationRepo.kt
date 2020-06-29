package com.eazyrento.customer.notification.model.repositry

import androidx.lifecycle.LiveData
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.customer.notification.model.NotificationDeleteModel
import com.eazyrento.customer.notification.model.NotificationList
import com.eazyrento.customer.notification.model.NotificationModel
import com.eazyrento.customer.notification.model.NotificationReadModel
import com.eazyrento.customer.notification.model.repositry.API.NotificationAPI
import com.eazyrento.webservice.ServiceGenrator
import com.google.gson.JsonElement

class NotificationRepo :
    GenericRequestHandler<NotificationList>(){

    fun getNotificationList(): LiveData<DataWrapper<NotificationList>> {
        val call = ServiceGenrator.client.create(
            NotificationAPI::class.java).getNotificationList()
        return doRequest(call)
    }

}

class NotificationOpretionRepo :
    GenericRequestHandler<JsonElement>(){

    fun deleteNotification(deleteModel: NotificationDeleteModel): LiveData<DataWrapper<JsonElement>> {
        val call = ServiceGenrator.client.create(
            NotificationAPI::class.java).deleteNotification(deleteModel)
        return doRequest(call)
    }

    fun readNotification(readModel: NotificationReadModel): LiveData<DataWrapper<JsonElement>> {
        val call = ServiceGenrator.client.create(
            NotificationAPI::class.java).readNotification(readModel)
        return doRequest(call)
    }

}