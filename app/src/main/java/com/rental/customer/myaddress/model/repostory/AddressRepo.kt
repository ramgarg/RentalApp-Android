package com.rental.customer.myaddress.model.repostory

import androidx.lifecycle.LiveData
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.appbiz.retrofitapi.GenericRequestHandler
import com.rental.common.model.modelclass.ProductDetailsResModel
import com.rental.common.model.repositry.api.MasterAPI
import com.rental.customer.myaddress.model.modelclass.AddressListResModel
import com.rental.customer.myaddress.model.repostory.api.AddressApi
import com.rental.webservice.ServiceGenrator

class AddressListRepo : GenericRequestHandler<AddressListResModel>(){

    fun getAddressList(): LiveData<DataWrapper<AddressListResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(AddressApi::class.java).getAddressList()
        return doRequest(call)
    }
}