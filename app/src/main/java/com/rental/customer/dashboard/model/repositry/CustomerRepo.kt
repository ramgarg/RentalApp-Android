package com.rental.customer.dashboard.model.repositry

import androidx.lifecycle.LiveData
import com.google.gson.JsonElement
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.appbiz.retrofitapi.GenericRequestHandler
import com.rental.customer.dashboard.model.modelclass.CustomerCreateBookingReqModel
import com.rental.customer.dashboard.model.modelclass.CustomerOrderListResModel
import com.rental.customer.dashboard.model.repositry.api.CustomerAPI
import com.rental.webservice.ServiceGenrator

class CustomerCreateBookingRepo :GenericRequestHandler<JsonElement>(){

    fun createBooking(customerCreateBookingReqModel: CustomerCreateBookingReqModel ): LiveData<DataWrapper<JsonElement>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(CustomerAPI::class.java).createBooking(customerCreateBookingReqModel)
        return doRequest(call)
    }


}

class CustomerOrderBookingOrderListRepo :GenericRequestHandler<CustomerOrderListResModel>(){

    fun getBookingOrdersList(value: Int): LiveData<DataWrapper<CustomerOrderListResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(CustomerAPI::class.java).getCustomerOrdersList(value)
        return doRequest(call)
    }

}
