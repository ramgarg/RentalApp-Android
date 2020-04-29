package com.eazyrento.login.model.repositry

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.customer.forgotpassword.model.modelClass.OTPRequest
import com.eazyrento.customer.forgotpassword.model.modelClass.OTPResponse
import com.eazyrento.login.model.modelclass.LoginUserReqModel
import com.eazyrento.login.model.modelclass.LoginUserResModel
import com.eazyrento.login.model.repositry.api.LoginAPI
import com.eazyrento.webservice.ServiceGenrator

class LoginUserRepo:
    GenericRequestHandler<LoginUserResModel>() {

    fun loginAPI( loginUserReqModel: LoginUserReqModel): LiveData<DataWrapper<LoginUserResModel>> {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            LoginAPI::class.java).login( loginUserReqModel)
        return doRequest(call)
    }

}

class LoginOTPRepo:
    GenericRequestHandler<OTPResponse>() {

    fun OTP_API( otpRequest: OTPRequest): LiveData<DataWrapper<OTPResponse>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            LoginAPI::class.java).otp(otpRequest)
        return doRequest(call)
    }

}