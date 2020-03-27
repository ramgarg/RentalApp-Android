package com.rental.customer.notification.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rental.webservice.APIServices
import com.rental.webservice.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class NotificationRepository {

    var apiServices:APIServices
    init {
       apiServices=RetrofitInstance.client.create(APIServices::class.java)
    }

    fun getNotificationList():LiveData<NotificationResponse>{
        val data: MutableLiveData<NotificationResponse> = MutableLiveData<NotificationResponse>()
        val call = apiServices.getNotification()
        call.enqueue(object :retrofit2.Callback<NotificationResponse>{
            override fun onFailure(call: Call<NotificationResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<NotificationResponse>, response: Response<NotificationResponse>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        return data
    }
}