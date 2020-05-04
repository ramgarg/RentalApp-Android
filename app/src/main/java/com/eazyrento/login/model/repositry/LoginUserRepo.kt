package com.eazyrento.login.model.repositry

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.customer.forgotpassword.model.modelClass.OTPRequest
import com.eazyrento.customer.forgotpassword.model.modelClass.OTPResponse
import com.eazyrento.login.model.modelclass.ForgotPasswordRequest
import com.eazyrento.login.model.modelclass.ForgotPasswordResponse
import com.eazyrento.login.model.modelclass.LoginUserReqModel
import com.eazyrento.login.model.modelclass.LoginUserResModel
import com.eazyrento.login.model.repositry.api.LoginAPI
import com.eazyrento.webservice.ServiceGenrator
import com.google.gson.JsonElement

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

class ForgetPasswordRepo:
    GenericRequestHandler<JsonElement>() {

    fun forget_password_api( forgotPasswordRequest: ForgotPasswordRequest): LiveData<DataWrapper<JsonElement>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            LoginAPI::class.java).forgotPassword(forgotPasswordRequest)
        return doRequest(call)
    }

}