package com.rental.customer.login.model.repositry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rental.customer.login.model.modelclass.LoginRequest
import com.rental.webservice.APIServices
import com.rental.webservice.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class LoginRespository() {

    private var apiclient: APIServices? = null

    init {
        apiclient = RetrofitInstance.client.create(APIServices::class.java)
    }

    fun loginAPI(loginRequest: LoginRequest): LiveData<LoginRequest> {
         val data: MutableLiveData<LoginRequest> = MutableLiveData<LoginRequest>()
        val call = apiclient?.login(loginRequest)
        call?.enqueue(object : retrofit2.Callback<LoginRequest> {
            override fun onFailure(call: Call<LoginRequest>, t: Throwable) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

            data.value=null
            }

            override fun onResponse(call: Call<LoginRequest>, response: Response<LoginRequest>) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

         data.value=response.body()
            }


        })
        return data
    }
}