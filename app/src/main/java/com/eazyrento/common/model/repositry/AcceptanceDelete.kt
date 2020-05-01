package com.eazyrento.common.model.repositry

import androidx.lifecycle.LiveData
import com.eazyrento.Constant
import com.eazyrento.agent.model.repositry.api.AgentAPI
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.common.model.modelclass.AcceptanceDeclineReqModel
import com.eazyrento.merchant.model.repository.api.MerchantAPI
import com.eazyrento.webservice.ServiceGenrator
import com.google.gson.JsonElement

class AcceptanceDeclineRepo : GenericRequestHandler<JsonElement>(){

    fun acceptDeclineRepo(acceptanceDeclineReqModel: AcceptanceDeclineReqModel,flag: Int): LiveData<DataWrapper<JsonElement>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        if (flag== Constant.AGENT_ACCEPTANCE)
            return doRequest( ServiceGenrator.client.create(
                AgentAPI::class.java).acceptanceDelete(acceptanceDeclineReqModel))

        else
            return doRequest( ServiceGenrator.client.create(
                MerchantAPI::class.java).acceptanceDelete(acceptanceDeclineReqModel))
    }
}