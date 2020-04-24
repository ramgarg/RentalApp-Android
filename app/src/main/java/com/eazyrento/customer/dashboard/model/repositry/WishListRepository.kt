package com.eazyrento.customer.dashboard.model.repositry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eazyrento.customer.dashboard.model.modelclass.HomeResponse
import com.eazyrento.webservice.APIServices
import com.eazyrento.webservice.ServiceGenrator
import retrofit2.Call
import retrofit2.Response


class WishListRepository() {

    var apiServices: APIServices

    init {
        apiServices = ServiceGenrator.client.create(
            APIServices::class.java)
    }

    fun getWishList():LiveData<HomeResponse> {
        val data: MutableLiveData<HomeResponse> = MutableLiveData<HomeResponse>()
        val call = apiServices?.getData()
        call?.enqueue(object : retrofit2.Callback<HomeResponse> {
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