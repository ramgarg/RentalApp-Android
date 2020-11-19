package com.eazyrento.common.model.repositry

import androidx.lifecycle.LiveData
import com.eazyrento.EazyRantoApplication
import com.eazyrento.Session
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.common.model.modelclass.*
import com.eazyrento.common.model.repositry.api.MasterAPI
import com.eazyrento.common.view.UserInfoAPP
import com.eazyrento.customer.dashboard.model.modelclass.OrderTrackingList
import com.eazyrento.customer.dashboard.model.modelclass.OrderTrackingListItem
import com.eazyrento.customer.dashboard.model.repositry.api.CustomerAPI
import com.eazyrento.webservice.ServiceGenrator
import com.google.gson.JsonElement
import retrofit2.Call

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

/*product list by sub cat*/

class ProductListByCatRepo : GenericRequestHandler<ProductListBySubCategoriesResModel>(){

    fun getProductListBySubCateg(name:String ): LiveData<DataWrapper<ProductListBySubCategoriesResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            MasterAPI::class.java).getProductListBySubCat(name)
        return doRequest(call)
    }
}

/*
* product deatils
* */

class ProductDetailsRepo : GenericRequestHandler<JsonElement>(){

    fun getProductDeatils(id:Int ): LiveData<DataWrapper<JsonElement>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            MasterAPI::class.java).getProductDetails(id)
        return doRequest(call)
    }
}


class LanguagChangeRepo : GenericRequestHandler<JsonElement>(){

    fun setLanguage(langaugeChangeReqModel: LangaugeChangeReqModel ): LiveData<DataWrapper<JsonElement>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            MasterAPI::class.java).langaugeChange(langaugeChangeReqModel)
        return doRequest(call)
    }
}

class OrderTrackingRepo :
    GenericRequestHandler<OrderTrackingListItem>() {

    fun getOrderTracking(orderID: String,subOrderID:Int): LiveData<DataWrapper<OrderTrackingListItem>> {

        lateinit var call: Call<OrderTrackingListItem>

            val classType = MasterAPI::class.java
            call = ServiceGenrator.client.create(
                classType).getOrderTracking(orderID ,subOrderID)

        return doRequest(call)
    }

}