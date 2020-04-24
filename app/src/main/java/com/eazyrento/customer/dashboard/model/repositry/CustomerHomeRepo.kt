package com.eazyrento.customer.dashboard.model.repositry

import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.common.model.modelclass.MasterResModel


class CustomerHomeRepo : GenericRequestHandler<MasterResModel>() {

    /*fun loginAPI( loginUserReqModel: LoginUserReqModel): LiveData<DataWrapper<LoginUserResModel>> {
        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = RetrofitInstance.client.create(LoginAPI::class.java).login( loginUserReqModel)
        return doRequest(call)
    }*/


}