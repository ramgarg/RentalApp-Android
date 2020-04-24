package com.eazyrento.common.model.repositry

import androidx.lifecycle.LiveData
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.common.model.modelclass.MasterResModel
import com.eazyrento.common.model.modelclass.ProductCategoriesResModel
import com.eazyrento.common.model.modelclass.ProductDetailsResModel
import com.eazyrento.common.model.modelclass.ProductSubCategoriesResModel
import com.eazyrento.common.model.repositry.api.MasterAPI
import com.eazyrento.webservice.ServiceGenrator
import com.google.gson.JsonElement

class MasterRepo : GenericRequestHandler<MasterResModel>(){

    fun getMasterData( ): LiveData<DataWrapper<MasterResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            MasterAPI::class.java).getMasterCategory()
        return doRequest(call)
    }
}

class ProductCategoriesRepo : GenericRequestHandler<JsonElement>(){

    fun getProductCateg(name:String ): LiveData<DataWrapper<JsonElement>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            MasterAPI::class.java).getProductCategory(name)
        return doRequest(call)
    }
}

class ProductSubCategoriesRepo : GenericRequestHandler<ProductSubCategoriesResModel>(){

    fun getProductSubCateg(name:String ): LiveData<DataWrapper<ProductSubCategoriesResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            MasterAPI::class.java).getProductSubCategory(name)
        return doRequest(call)
    }
}

/*
* product deatils
* */

class ProductDetailsRepo : GenericRequestHandler<ProductDetailsResModel>(){

    fun getProductDeatils(id:Int ): LiveData<DataWrapper<ProductDetailsResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            MasterAPI::class.java).getProductDetails(id)
        return doRequest(call)
    }
}