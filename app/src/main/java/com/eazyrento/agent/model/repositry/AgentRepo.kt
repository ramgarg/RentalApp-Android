package com.eazyrento.agent.model.repositry

import androidx.lifecycle.LiveData
import com.eazyrento.agent.model.modelclass.AgentAddNoteReqModel
import com.eazyrento.agent.model.modelclass.AgentMerchantFindNearByResModel
import com.eazyrento.agent.model.modelclass.AgentNotesListResModel
import com.eazyrento.agent.model.modelclass.AssignMerchantsReqModel
import com.eazyrento.agent.model.repositry.api.AgentAPI

import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.eazyrento.webservice.ServiceGenrator
import com.google.gson.JsonElement



class AgentMerchantsAssignRepo :
    GenericRequestHandler<JsonElement>(){

    fun assignMerchants(assignMerchantsReqModel: AssignMerchantsReqModel): LiveData<DataWrapper<JsonElement>> {
        val call = ServiceGenrator.client.create(
            AgentAPI::class.java).assignMerchants(assignMerchantsReqModel)
        return doRequest(call)
    }

}

class AgentBookingDetailsRepo :
    GenericRequestHandler<CustomerOrderDetailsResModel>(){

    fun agentBookingDetails(id: Int): LiveData<DataWrapper<CustomerOrderDetailsResModel>> {
        val call = ServiceGenrator.client.create(
            AgentAPI::class.java).getAgentBookingDetail(id)
        return doRequest(call)
    }

}



class AgentMerchantNearByRepo :
    GenericRequestHandler<AgentMerchantFindNearByResModel>(){

    fun getAgentMerchantNearBy(id:Int): LiveData<DataWrapper<AgentMerchantFindNearByResModel>> {
        val call = ServiceGenrator.client.create(
            AgentAPI::class.java).getAgentMerchantsNearBy(id)
        return doRequest(call)
    }

}

class AgentNotesListRepo :
    GenericRequestHandler<AgentNotesListResModel>(){

    fun getAgentNotesList(): LiveData<DataWrapper<AgentNotesListResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            AgentAPI::class.java).getAgentNotes()
        return doRequest(call)
    }

}

class AgentAddNotesRepo :
    GenericRequestHandler<JsonElement>(){

    fun agentNotesAdd(agentAddNoteReqModel: AgentAddNoteReqModel): LiveData<DataWrapper<JsonElement>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            AgentAPI::class.java).createAgentNote(agentAddNoteReqModel)
        return doRequest(call)
    }

}



