package com.eazyrento.common.model.repositry

import androidx.lifecycle.LiveData
import com.eazyrento.Constant
import com.eazyrento.agent.model.repositry.api.AgentAPI
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.common.model.modelclass.BookingDashboardResModel
import com.eazyrento.common.model.modelclass.BookingListResModel
import com.eazyrento.customer.dashboard.model.repositry.api.CustomerAPI
import com.eazyrento.merchant.model.repository.api.MerchantAPI
import com.eazyrento.webservice.ServiceGenrator

class BookingDashboardRepo : GenericRequestHandler<BookingDashboardResModel>(){

    fun getDeshboardData(value: Int): LiveData<DataWrapper<BookingDashboardResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        if (value== Constant.BOOKING_DASHBOARD_MERCHANT)
             return doRequest( ServiceGenrator.client.create(
                 MerchantAPI::class.java).getMerchantDashboradData())
        else
            return doRequest( ServiceGenrator.client.create(
                AgentAPI::class.java).getAgentDashboardData())
    }



}
class MyBookingListRepo : GenericRequestHandler<BookingListResModel>(){

    fun getBookingListData(value: Int): LiveData<DataWrapper<BookingListResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        if (value== Constant.BOOKING_LIST_CUSTOMER)
            return doRequest( ServiceGenrator.client.create(
                CustomerAPI::class.java).getCustomerBookingList())
        else
            return doRequest( ServiceGenrator.client.create(
                AgentAPI::class.java).getCustomerBookingList())
    }


}