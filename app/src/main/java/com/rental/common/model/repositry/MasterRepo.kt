package com.rental.common.model.repositry

import androidx.lifecycle.LiveData
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.appbiz.retrofitapi.GenericRequestHandler
import com.rental.common.model.modelclass.MasterResModel
import com.rental.common.model.modelclass.ProductCategoriesResModel
import com.rental.common.model.modelclass.ProductSubCategoriesResModel
import com.rental.common.model.repositry.api.MasterAPI
import com.rental.webservice.ServiceGenrator

class MasterRepo : GenericRequestHandler<MasterResModel>(){

    fun getMasterData( ): LiveData<DataWrapper<MasterResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(MasterAPI::class.java).getMasterCategory()
        return doRequest(call)
    }
}

class ProductCategoriesRepo : GenericRequestHandler<ProductCategoriesResModel>(){

    fun getProductCateg(name:String ): LiveData<DataWrapper<ProductCategoriesResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(MasterAPI::class.java).getProductCategory(name)
        return doRequest(call)
    }
}

class ProductSubCategoriesRepo : GenericRequestHandler<ProductSubCategoriesResModel>(){

    fun getProductSubCateg(name:String ): LiveData<DataWrapper<ProductSubCategoriesResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(MasterAPI::class.java).getProductSubCategory(name)
        return doRequest(call)
    }
}