package com.rental.login.model.repositry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.rental.appbiz.AppBizLogger
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.appbiz.retrofitapi.GenericRequestHandler
import com.rental.login.model.modelclass.LoginRequest
import com.rental.login.model.modelclass.LoginUserReqModel
import com.rental.login.model.modelclass.LoginUserResModel
import com.rental.login.model.repositry.api.LoginAPI
import com.rental.webservice.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class LoginUserRepo:GenericRequestHandler<LoginUserResModel>() {

    private var call: Call<LoginUserResModel>? = null


    fun loginAPI( loginUserReqModel: LoginUserReqModel): LiveData<DataWrapper<LoginUserResModel>> {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        call = RetrofitInstance.client.create(LoginAPI::class.java).login( loginUserReqModel)

        return doRequest()
    }

    override fun makeRequest(): Call<LoginUserResModel> {
        return call!!
    }

}