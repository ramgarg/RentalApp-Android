package com.eazyrento.common.model.repositry

import androidx.lifecycle.LiveData
import com.eazyrento.Constant
import com.eazyrento.agent.model.repositry.api.AgentAPI
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.common.model.modelclass.BookingDashboardResModel
import com.eazyrento.common.model.modelclass.BookingListResModel
import com.eazyrento.common.model.modelclass.DriverList
import com.eazyrento.common.model.modelclass.MerchantAssignDriver
import com.eazyrento.common.model.repositry.api.DriverApis
import com.eazyrento.customer.dashboard.model.repositry.api.CustomerAPI
import com.eazyrento.merchant.model.repository.api.MerchantAPI
import com.eazyrento.webservice.ServiceGenrator

class CustomerDriverListingRepo : GenericRequestHandler<DriverList>() {

    fun getNearByDriversToCustomer(lati:Double,longi:Double): LiveData<DataWrapper<DriverList>> {
        return doRequest(
            ServiceGenrator.client.create(
                DriverApis::class.java
            ).getNearByDriversToCustomer(lati, longi)
        )

    }
}

class AgentDriverListingRepo : GenericRequestHandler<DriverList>() {

    fun getNearByDriversToAgent(bookingID:Int): LiveData<DataWrapper<DriverList>> {
        return doRequest(
            ServiceGenrator.client.create(
                DriverApis::class.java
            ).getNearByDriversToAgent(bookingID)
        )

    }


}

class MerchantDriverListingRepo : GenericRequestHandler<DriverList>() {

    fun getNearByDriversToMerchant(lati:Double,longi:Double): LiveData<DataWrapper<DriverList>> {
        return doRequest(
            ServiceGenrator.client.create(
                DriverApis::class.java
            ).getMerchantDrivers(lati, longi)
        )

    }
}

