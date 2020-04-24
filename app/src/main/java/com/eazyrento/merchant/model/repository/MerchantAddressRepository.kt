package com.eazyrento.merchant.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eazyrento.customer.dashboard.model.modelclass.HomeResponse
import com.eazyrento.webservice.APIServices
import com.eazyrento.webservice.ServiceGenrator
import retrofit2.Call
import retrofit2.Response

class MerchantAddressRepository {

    var apiServices: APIServices

    init {
        apiServices= ServiceGenrator.client.create(
            APIServices::class.java)
    }

    fun getMyAddress(): LiveData<HomeResponse> {
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