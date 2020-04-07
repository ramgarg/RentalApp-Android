package com.rental.login.model.repositry

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.rental.appbiz.AppBizLogger
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.appbiz.retrofitapi.GenericRequestHandler
import com.rental.login.model.modelclass.RegisterUserReqModel
import com.rental.login.model.modelclass.RegisterUserResModel
import com.rental.login.model.repositry.api.LoginAPI
import com.rental.webservice.ServiceGenrator

class RegisterUserRepo : GenericRequestHandler<RegisterUserResModel>(){

    fun registrationAPI(registerUserReqModel: RegisterUserReqModel): LiveData<DataWrapper<RegisterUserResModel>> {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(registerUserReqModel))
        val call = ServiceGenrator.client.create(LoginAPI::class.java).register(registerUserReqModel)

        return doRequest(call)
    }
}