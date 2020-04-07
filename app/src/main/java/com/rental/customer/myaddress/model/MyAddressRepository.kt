package com.rental.customer.myaddress.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import com.rental.webservice.APIServices
import com.rental.webservice.ServiceGenrator
import retrofit2.Call
import retrofit2.Response

class MyAddressRepository {

    var apiServices:APIServices

    init {
        apiServices=ServiceGenrator.client.create(APIServices::class.java)
    }

    fun getMyAddress():LiveData<HomeResponse>{
        val data: MutableLiveData<HomeResponse> = MutableLiveData<HomeResponse>()
        val call=apiServices?.getData()

        call?.enqueue(object :retrofit2.Callback<HomeResponse>{
            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                data.value=null
            }

            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                data.value=response.body()
            }

        })

        return data

    }
}