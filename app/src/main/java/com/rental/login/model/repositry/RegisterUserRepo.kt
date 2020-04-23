package com.rental.login.model.repositry

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.rental.appbiz.AppBizLogger
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.appbiz.retrofitapi.GenericRequestHandler
import com.rental.login.model.modelclass.ProfileModelReqRes
import com.rental.login.model.modelclass.RegisterUserReqModel
import com.rental.login.model.modelclass.RegisterUserResModel
import com.rental.login.model.modelclass.UserProfile
import com.rental.login.model.repositry.api.LoginAPI
import com.rental.webservice.ServiceGenrator

class RegisterUserRepo : GenericRequestHandler<RegisterUserResModel>(){

    fun registrationAPI(registerUserReqModel: RegisterUserReqModel): LiveData<DataWrapper<RegisterUserResModel>> {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(registerUserReqModel))
        val call = ServiceGenrator.client.create(LoginAPI::class.java).register(registerUserReqModel)

        return doRequest(call)
    }
}

class ProfileUserRepo : GenericRequestHandler<ProfileModelReqRes>(){

    fun getProfile(): LiveData<DataWrapper<ProfileModelReqRes>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(registerUserReqModel))
        val call = ServiceGenrator.client.create(LoginAPI::class.java).getUserProfile()

        return doRequest(call)
    }
}

class UpdateProfileUserRepo : GenericRequestHandler<JsonElement>(){

    fun updateProfile(userProfile: UserProfile): LiveData<DataWrapper<JsonElement>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(registerUserReqModel))
        val call = ServiceGenrator.client.create(LoginAPI::class.java).updateUserProfile(userProfile)

        return doRequest(call)
    }
}