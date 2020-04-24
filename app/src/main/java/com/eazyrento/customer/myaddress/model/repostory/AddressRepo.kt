package com.eazyrento.customer.myaddress.model.repostory

import androidx.lifecycle.LiveData
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.common.model.modelclass.ProductDetailsResModel
import com.eazyrento.common.model.repositry.api.MasterAPI
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModel
import com.eazyrento.customer.myaddress.model.repostory.api.AddressApi
import com.eazyrento.webservice.ServiceGenrator

class AddressListRepo : GenericRequestHandler<AddressListResModel>(){

    fun getAddressList(): LiveData<DataWrapper<AddressListResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            AddressApi::class.java).getAddressList()
        return doRequest(call)
    }
}