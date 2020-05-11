package com.eazyrento.customer.myaddress.model.repostory

import androidx.lifecycle.LiveData
import com.eazyrento.agent.model.modelclass.AgentAddNoteReqModel
import com.eazyrento.agent.model.repositry.api.AgentAPI
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.common.model.modelclass.ProductDetailsResModel
import com.eazyrento.common.model.repositry.api.MasterAPI
import com.eazyrento.customer.myaddress.model.modelclass.AddressCreateReqModel
import com.eazyrento.customer.myaddress.model.modelclass.AddressDetailsResModel
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModel
import com.eazyrento.customer.myaddress.model.repostory.api.AddressApi
import com.eazyrento.login.model.modelclass.AddressInfo
import com.eazyrento.merchant.model.modelclass.MerchantAddProductReqModel
import com.eazyrento.merchant.model.modelclass.MerchantProductDetailsResModel
import com.eazyrento.merchant.model.repository.api.MerchantAPI
import com.eazyrento.webservice.ServiceGenrator
import com.google.gson.JsonElement
import retrofit2.create

class AddressListRepo : GenericRequestHandler<AddressListResModel>(){

    fun getAddressList(): LiveData<DataWrapper<AddressListResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            AddressApi::class.java).getAddressList()
        return doRequest(call)
    }
}

class AddressCreateRepo :
    GenericRequestHandler<AddressInfo>(){

    fun addressCreate(addressCreateReqModelItem: AddressInfo): LiveData<DataWrapper<AddressInfo>> {

            val call = ServiceGenrator.client.create(
                AddressApi::class.java).createAddress(addressCreateReqModelItem)
            return doRequest(call)
        }

    }
class AddressUpdateRepo :
    GenericRequestHandler<JsonElement>(){

    fun addressUpdate(addressCreateReqModelItem: AddressInfo,id: Int): LiveData<DataWrapper<JsonElement>> {

        val call = ServiceGenrator.client.create(
            AddressApi::class.java).updateAddress(id,addressCreateReqModelItem)
        return doRequest(call)
    }

}


class AddressDeleteRepo : GenericRequestHandler<JsonElement>(){

    fun deleteAddress(id: Int): LiveData<DataWrapper<JsonElement>> {
        val call = ServiceGenrator.client.create(
            AddressApi::class.java).deleteAddress(id)
        return doRequest(call)
    }
}

class AddressDetailsRepo : GenericRequestHandler<AddressDetailsResModel>(){

    fun addressDetails(id: Int): LiveData<DataWrapper<AddressDetailsResModel>> {
        val call = ServiceGenrator.client.create(
            AddressApi::class.java).getAddressDetails(id)
        return doRequest(call)
    }
}