package com.eazyrento.customer.notification.model.repositry

import androidx.lifecycle.LiveData
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.customer.notification.model.NotificationList
import com.eazyrento.customer.notification.model.NotificationModel
import com.eazyrento.customer.notification.model.repositry.API.NotificationAPI
import com.eazyrento.webservice.ServiceGenrator

class NotificationRepo :
    GenericRequestHandler<NotificationList>(){

    fun getNotificationList(): LiveData<DataWrapper<NotificationList>> {
        val call = ServiceGenrator.client.create(
            NotificationAPI::class.java).getNotificationList()
        return doRequest(call)
    }

}