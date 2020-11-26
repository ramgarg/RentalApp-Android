package com.eazyrento.customer.notification.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.customer.notification.model.NotificationDeleteModel
import com.eazyrento.customer.notification.model.NotificationList
import com.eazyrento.customer.notification.model.NotificationReadModel
import com.eazyrento.customer.notification.model.repositry.NotificationOpretionRepo
import com.eazyrento.customer.notification.model.repositry.NotificationRepo
import com.google.gson.JsonElement

class NotificationViewModel : ViewModel() {

    fun getNotificationList(): LiveData<DataWrapper<NotificationList>> {
        return NotificationRepo()
            .getNotificationList()
    }

    fun deleteNotification(deleteModel: NotificationDeleteModel): LiveData<DataWrapper<JsonElement>> {
        return NotificationOpretionRepo()
            .deleteNotification(deleteModel)
    }

    fun readNotification(readModel: NotificationReadModel): LiveData<DataWrapper<JsonElement>> {
        return NotificationOpretionRepo()
            .readNotification(readModel)
    }
}