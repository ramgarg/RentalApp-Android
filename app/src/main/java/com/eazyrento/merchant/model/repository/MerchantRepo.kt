package com.eazyrento.merchant.model.repository

import androidx.lifecycle.LiveData
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.common.model.modelclass.ProductCategoriesResModel
import com.eazyrento.common.model.modelclass.BookingDashboardResModel
import com.eazyrento.merchant.model.modelclass.MerchantAddProductReqModel
import com.eazyrento.merchant.model.repository.api.MerchantAPI
import com.eazyrento.webservice.ServiceGenrator
import com.google.gson.JsonElement


class MerchantProductCategoriesRepo : GenericRequestHandler<JsonElement>(){

    fun getProductCateg( ): LiveData<DataWrapper<JsonElement>> {
        val call = ServiceGenrator.client.create(
            MerchantAPI::class.java).getProductCategory()
        return doRequest(call)
    }
}

class MerchantAddProductRepo : GenericRequestHandler<JsonElement>(){

    fun addMerchantProduct(merchantAddProductReqModel: MerchantAddProductReqModel): LiveData<DataWrapper<JsonElement>> {
        val call = ServiceGenrator.client.create(
            MerchantAPI::class.java).addProduct(merchantAddProductReqModel)
        return doRequest(call)
    }
}

class MerchantDeleteProductRepo : GenericRequestHandler<JsonElement>(){

    fun deleteProduct(id: Int): LiveData<DataWrapper<JsonElement>> {
        val call = ServiceGenrator.client.create(
            MerchantAPI::class.java).deleteProduct(id)
        return doRequest(call)
    }
}

/*class MerchantProductCategories :GenericRequestHandler<ProductCategoriesResModel>(){

    fun getBookingOrdersList(value: Int): LiveData<DataWrapper<ProductCategoriesResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(MerchantAPI::class.java).getProductCategory()
        return doRequest(call)
    }

}*/

/*class CustomerOrderDetailsRepo :GenericRequestHandler<CustomerOrderDetailsResModel>(){

    fun getCustomerOrderDetail(value: Int): LiveData<DataWrapper<CustomerOrderDetailsResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(MerchantAPI::class.java).getCustomerOrderDetail(value)
        return doRequest(call)
    }

}*/


