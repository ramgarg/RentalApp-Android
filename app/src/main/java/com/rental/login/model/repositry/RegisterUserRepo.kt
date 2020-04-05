package com.rental.login.model.repositry

import androidx.lifecycle.LiveData
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.appbiz.retrofitapi.GenericRequestHandler
import com.rental.login.model.modelclass.RegisterUserReqModel
import com.rental.login.model.modelclass.RegisterUserResModel
import com.rental.login.model.repositry.api.LoginAPI
import com.rental.webservice.RetrofitInstance
import retrofit2.Call

class RegisterUserRepo : GenericRequestHandler<RegisterUserResModel>(){

    private var call: Call<RegisterUserResModel>? = null


    fun registrationAPI(registerUserReqModel: RegisterUserReqModel): LiveData<DataWrapper<RegisterUserResModel>> {
             call = RetrofitInstance.client.create(LoginAPI::class.java).register(registerUserReqModel)

             return doRequest()
    }

    override fun makeRequest(): Call<RegisterUserResModel> {
        return call!!
    }
}