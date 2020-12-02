package com.eazyrento.login.model.repositry

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.eazyrento.appbiz.AppBizLogger
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.login.model.modelclass.*
import com.eazyrento.login.model.repositry.api.LoginAPI
import com.eazyrento.webservice.ServiceGenrator

class RegisterUserRepo : GenericRequestHandler<RegisterUserResModel>(){

    fun registrationAPI(registerUserReqModel: RegisterUserReqModel): LiveData<DataWrapper<RegisterUserResModel>> {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(registerUserReqModel))
        val call = ServiceGenrator.client.create(
            LoginAPI::class.java).register(registerUserReqModel)

        return doRequest(call)
    }
}

class ProfileUserRepo : GenericRequestHandler<ProfileModelReqRes>(){

    fun getProfile(): LiveData<DataWrapper<ProfileModelReqRes>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(registerUserReqModel))
        val call = ServiceGenrator.client.create(
            LoginAPI::class.java).getUserProfile()

        return doRequest(call)
    }
}




class UpdateProfileUserRepo : GenericRequestHandler<JsonElement>(){

    fun updateProfile(userProfile: UserProfile): LiveData<DataWrapper<JsonElement>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(registerUserReqModel))
        val call = ServiceGenrator.client.create(
            LoginAPI::class.java).updateUserProfile(userProfile)

        return doRequest(call)
    }
}


class DashboardUserRepo : GenericRequestHandler<DashboardModel>(){

    fun getDashboard(): LiveData<DataWrapper<DashboardModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(registerUserReqModel))
        val call = ServiceGenrator.client.create(
            LoginAPI::class.java).getUserDASHBOARD()

        return doRequest(call)
    }
}