package com.rental.customer.notification.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.customer.notification.model.NotificationRepository
import com.rental.customer.notification.model.NotificationResponse

class NotificationViewModel :ViewModel() {

    var  notificationRepository:NotificationRepository
    var responseNotificationLiveData:LiveData<NotificationResponse>

    init {
        notificationRepository=NotificationRepository()
        responseNotificationLiveData=notificationRepository.getNotificationList()
    }

    fun getNotificationResponse():LiveData<NotificationResponse>{

      return  responseNotificationLiveData
    }

}