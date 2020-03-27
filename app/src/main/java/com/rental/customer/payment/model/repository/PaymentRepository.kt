package com.rental.customer.payment.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import com.rental.webservice.APIServices
import com.rental.webservice.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class PaymentRepository {

    var apiServices: APIServices

    init {
        apiServices = RetrofitInstance.client.create(APIServices::class.java)

    }

    fun getPaymentHistoryList(): LiveData<HomeResponse>{
        val data: MutableLiveData<HomeResponse> = MutableLiveData<HomeResponse>()
        val call = apiServices.getData()
        call.enqueue(object : retrofit2.Callback<HomeResponse> {
            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                data.value=null
            }

            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                data.value=response.body()
            }


        })
        return data
    }
}