package com.eazyrento.agent.model.repositry

import androidx.lifecycle.LiveData
import com.eazyrento.agent.model.modelclass.*
import com.eazyrento.agent.model.repositry.api.AgentAPI

import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.customer.dashboard.model.modelclass.CustomerFeedbackRequestModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.eazyrento.customer.dashboard.model.repositry.api.CustomerAPI
import com.eazyrento.merchant.model.modelclass.MerchantAddProductReqModel
import com.eazyrento.merchant.model.repository.api.MerchantAPI
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

class AgentUpdateOrderRepo :
    GenericRequestHandler<JsonElement>(){

    fun updateOrder(id:Int,updateModel: CustomerOrderDetailsResModel): LiveData<DataWrapper<JsonElement>> {
        val call = ServiceGenrator.client.create(
            AgentAPI::class.java).updateCustomerOrderDetail(id,updateModel)
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
    GenericRequestHandler<AgentNotesListResModelItem>(){

    fun agentNotesAdd(agentAddNoteReqModel: AgentAddNoteReqModelItem): LiveData<DataWrapper<AgentNotesListResModelItem>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            AgentAPI::class.java).createAgentNote(agentAddNoteReqModel)
        return doRequest(call)
    }

}

class AgentNoteDeleteRepo : GenericRequestHandler<JsonElement>(){

    fun deleteNote(id: Int): LiveData<DataWrapper<JsonElement>> {
        val call = ServiceGenrator.client.create(
            AgentAPI::class.java).deleteNote(id)
        return doRequest(call)
    }
}

class AgentUpdateNoteRepo : GenericRequestHandler<AgentNotesListResModelItem>(){

    fun updateNote( id:Int,agentAddNoteReqModelItem: AgentAddNoteReqModelItem): LiveData<DataWrapper<AgentNotesListResModelItem>> {

        val call = ServiceGenrator.client.create(
            AgentAPI::class.java).updateNote(id,agentAddNoteReqModelItem)
           return doRequest(call)
    }
}

class AgentFeedbackRepo :
    GenericRequestHandler<JsonElement>(){

    fun agentFeedback(agentFeedbackReqModel: AgentFeedbackReqModel): LiveData<DataWrapper<JsonElement>> {

        val call = ServiceGenrator.client.create(
            AgentAPI::class.java).agentFeedback(agentFeedbackReqModel)
        return doRequest(call)
    }

}


