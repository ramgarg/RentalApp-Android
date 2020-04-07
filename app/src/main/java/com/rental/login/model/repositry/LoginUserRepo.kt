package com.rental.login.model.repositry

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.rental.appbiz.AppBizLogger
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.appbiz.retrofitapi.GenericRequestHandler
import com.rental.login.model.modelclass.LoginUserReqModel
import com.rental.login.model.modelclass.LoginUserResModel
import com.rental.login.model.repositry.api.LoginAPI
import com.rental.webservice.ServiceGenrator

class LoginUserRepo:GenericRequestHandler<LoginUserResModel>() {

    fun loginAPI( loginUserReqModel: LoginUserReqModel): LiveData<DataWrapper<LoginUserResModel>> {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(LoginAPI::class.java).login( loginUserReqModel)
        return doRequest(call)
    }

}