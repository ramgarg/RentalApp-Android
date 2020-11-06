package com.eazyrento.merchant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.DriverList
import com.eazyrento.common.model.modelclass.MerchantAssignDriver
import com.eazyrento.common.model.repositry.CustomerDriverListingRepo
import com.eazyrento.common.model.repositry.MerchantDriverListingRepo
import com.eazyrento.customer.dashboard.model.modelclass.HomeResponse
import com.eazyrento.merchant.model.repository.MerchantAssignDriverRepo
import com.eazyrento.merchant.model.repository.MerchantOrderRepository
import com.google.gson.JsonElement

class MerchantAssignDriverViewModel : ViewModel() {

    fun merchantAssignDrivers(merchantAssignDriver: MerchantAssignDriver): LiveData<DataWrapper<JsonElement>> {
        return MerchantAssignDriverRepo().merchantAssignDrivers(merchantAssignDriver)
    }
}