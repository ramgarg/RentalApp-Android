package com.eazyrento.customer.dashboard.model.repositry

import androidx.lifecycle.LiveData
import com.google.gson.JsonElement
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerWishListResModel
import com.eazyrento.customer.dashboard.model.repositry.api.CustomerAPI
import com.eazyrento.webservice.ServiceGenrator

class CustomerCreateBookingRepo :
    GenericRequestHandler<JsonElement>(){

    fun createBooking(customerCreateBookingReqModel: CustomerCreateBookingReqModel): LiveData<DataWrapper<JsonElement>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            CustomerAPI::class.java).createBooking(customerCreateBookingReqModel)
        return doRequest(call)
    }


}

class CustomerOrderBookingOrderListRepo :
    GenericRequestHandler<CustomerOrderListResModel>(){

    fun getBookingOrdersList(value: Int): LiveData<DataWrapper<CustomerOrderListResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            CustomerAPI::class.java).getCustomerOrdersList(value)
        return doRequest(call)
    }

}

class CustomerOrderDetailsRepo :
    GenericRequestHandler<CustomerOrderDetailsResModel>(){

    fun getCustomerOrderDetail(value: Int): LiveData<DataWrapper<CustomerOrderDetailsResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            CustomerAPI::class.java).getCustomerOrderDetail(value)
        return doRequest(call)
    }

}

class CustomerWishListRepo :
    GenericRequestHandler<CustomerWishListResModel>(){

    fun getCustomerWishList(): LiveData<DataWrapper<CustomerWishListResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            CustomerAPI::class.java).getWishList()
        return doRequest(call)
    }

}


