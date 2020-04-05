package com.rental.login.model.repositry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rental.login.model.modelclass.LoginUserRequest
import com.rental.login.model.repositry.api.LoginAPI
import com.rental.webservice.APIServices
import com.rental.webservice.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class LoginUserRepo() {

    private var apiclient: LoginAPI? = null

    init {
        apiclient = RetrofitInstance.client.create(LoginAPI::class.java)
    }

    fun loginAPI(loginRequest: LoginUserRequest): LiveData<LoginUserRequest> {
         val data: MutableLiveData<LoginUserRequest> = MutableLiveData<LoginUserRequest>()
        val call = apiclient?.login(loginRequest)
        call?.enqueue(object : retrofit2.Callback<LoginUserRequest> {
            override fun onFailure(call: Call<LoginUserRequest>, t: Throwable) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

            data.value=null
            }

            override fun onResponse(call: Call<LoginUserRequest>, response: Response<LoginUserRequest>) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

         data.value=response.body()
            }


        })
        return data
    }
}