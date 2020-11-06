package com.eazyrento.merchant.model.repository

import androidx.lifecycle.LiveData
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.common.model.modelclass.DriverList
import com.eazyrento.common.model.modelclass.MerchantAssignDriver
import com.eazyrento.common.model.repositry.api.DriverApis
import com.eazyrento.webservice.ServiceGenrator
import com.google.gson.JsonElement


class MerchantAssignDriverRepo : GenericRequestHandler<JsonElement>() {

        fun merchantAssignDrivers(merchantAssignDriver: MerchantAssignDriver): LiveData<DataWrapper<JsonElement>> {
            return doRequest(
                ServiceGenrator.client.create(
                    DriverApis::class.java
                ).merchantAssignDrivers(merchantAssignDriver)
            )

        }

}