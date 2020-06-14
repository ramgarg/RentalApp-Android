package com.eazyrento.customer.notification.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.customer.notification.model.NotificationList
import com.eazyrento.customer.notification.model.NotificationModel
import com.eazyrento.customer.notification.model.repositry.NotificationRepo

class NotificationViewModel : ViewModel() {

    fun getNotificationList(): LiveData<DataWrapper<NotificationList>> {
        return NotificationRepo()
            .getNotificationList()
    }
}