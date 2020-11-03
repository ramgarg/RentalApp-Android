package com.eazyrento.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.AcceptanceDeclineReqModel
import com.eazyrento.common.model.modelclass.DriverList
import com.eazyrento.common.model.repositry.AcceptanceDeclineRepo
import com.eazyrento.common.model.repositry.AgentDriverListingRepo
import com.eazyrento.common.model.repositry.CustomerDriverListingRepo
import com.eazyrento.common.model.repositry.MerchantDriverListingRepo
import com.eazyrento.customer.dashboard.model.repositry.CustomerWishDeleteRepo
import com.google.gson.JsonElement

class DriverListingViewModel: ViewModel() {

    fun getNearByDriversToCustomer(lati:Double , longi:Double): LiveData<DataWrapper<DriverList>> {
        return CustomerDriverListingRepo()
            .getNearByDriversToCustomer(lati, longi)
    }

    fun getNearByDriversToAgent(bookingID:Int): LiveData<DataWrapper<DriverList>> {
        return AgentDriverListingRepo()
            .getNearByDriversToAgent(bookingID)
    }

    fun getMerchantDrivers(lati:Double , longi:Double): LiveData<DataWrapper<DriverList>> {
        return MerchantDriverListingRepo()
            .getNearByDriversToMerchant(lati, longi)
    }
}