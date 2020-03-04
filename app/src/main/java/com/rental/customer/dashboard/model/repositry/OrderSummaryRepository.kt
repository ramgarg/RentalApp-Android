package com.rental.customer.dashboard.model.repositry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import com.rental.customer.dashboard.model.modelclass.OrderSummaryResponseModel
import com.rental.customer.webservice.APIServices
import com.rental.customer.webservice.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class OrderSummaryRepository {
    var apiServices: APIServices

    init {
        apiServices = RetrofitInstance.client.create(APIServices::class.java)

    }

    fun getOrderSummary(): LiveData<OrderSummaryResponseModel> {
        val data: MutableLiveData<OrderSummaryResponseModel> = MutableLiveData<OrderSummaryResponseModel>()
        val call = apiServices?.getOrderSummary()
        call?.enqueue(object : retrofit2.Callback<OrderSummaryResponseModel> {
            override fun onFailure(call: Call<OrderSummaryResponseModel>, t: Throwable) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                data.value=null
            }

            override fun onResponse(call: Call<OrderSummaryResponseModel>, response: Response<OrderSummaryResponseModel>) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                data.value=response.body()
            }
        })
        return data

    }
}