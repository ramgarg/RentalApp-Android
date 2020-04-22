package com.rental.common.model.repositry

import androidx.lifecycle.LiveData
import com.rental.Constant
import com.rental.agent.model.repositry.api.AgentAPI
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.appbiz.retrofitapi.GenericRequestHandler
import com.rental.common.model.modelclass.BookingDashboardResModel
import com.rental.common.model.modelclass.BookingListResModel
import com.rental.customer.dashboard.model.repositry.api.CustomerAPI
import com.rental.merchant.model.repository.api.MerchantAPI
import com.rental.webservice.ServiceGenrator

class BookingDashboardRepo : GenericRequestHandler<BookingDashboardResModel>(){

    fun getDeshboardData(value: Int): LiveData<DataWrapper<BookingDashboardResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        if (value==Constant.BOOKING_DASHBOARD_MERCHANT)
             return doRequest( ServiceGenrator.client.create(MerchantAPI::class.java).getMerchantDashboradData())
        else
            return doRequest( ServiceGenrator.client.create(AgentAPI::class.java).getAgentDashboardData())
    }



}
class MyBookingListRepo : GenericRequestHandler<BookingListResModel>(){

    fun getBookingListData(value: Int): LiveData<DataWrapper<BookingListResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        if (value==Constant.BOOKING_LIST_CUSTOMER)
            return doRequest( ServiceGenrator.client.create(CustomerAPI::class.java).getCustomerBookingList())
        else
            return doRequest( ServiceGenrator.client.create(AgentAPI::class.java).getCustomerBookingList())
    }


}