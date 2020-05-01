package com.eazyrento.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.AcceptanceDeclineReqModel
import com.eazyrento.common.model.repositry.AcceptanceDeclineRepo
import com.eazyrento.customer.dashboard.model.repositry.CustomerWishDeleteRepo
import com.google.gson.JsonElement

class AcceptanceDeleteViewModel: ViewModel() {

    fun accptanceDecline(acceptanceDeclineReqModel: AcceptanceDeclineReqModel,id: Int): LiveData<DataWrapper<JsonElement>> {
        return AcceptanceDeclineRepo()
            . acceptDeclineRepo(acceptanceDeclineReqModel,id)
    }
}