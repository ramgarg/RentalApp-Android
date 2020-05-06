package com.eazyrento.agent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.agent.model.modelclass.*
import com.eazyrento.agent.model.repositry.*
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel

import com.google.gson.JsonElement


class AgentBookingDetailsViewModel : ViewModel() {

    fun agentBookingDetails(id: Int): LiveData<DataWrapper<CustomerOrderDetailsResModel>> {
        return AgentBookingDetailsRepo().agentBookingDetails(id)

    }
}

class AgentAssignMerchantViewModel : ViewModel() {

    fun assignMerchants(assignMerchantsReqModel: AssignMerchantsReqModel): LiveData<DataWrapper<JsonElement>> {
        return AgentMerchantsAssignRepo().assignMerchants(assignMerchantsReqModel)

    }
}

class AgentMerchantNeearByViewModel : ViewModel() {

    fun nearMerchantBy(id: Int): LiveData<DataWrapper<AgentMerchantFindNearByResModel>> {
        return AgentMerchantNearByRepo().getAgentMerchantNearBy(id)

    }
}

class AgentCreateNotesViewModel : ViewModel() {

    fun createNote(agentAddNoteReqModelItem: AgentAddNoteReqModelItem): LiveData<DataWrapper<AgentAddNoteReqModelItem>> {
        return AgentAddNotesRepo()
            .agentNotesAdd(agentAddNoteReqModelItem)
    }
}

class AgentNotesListViewModel : ViewModel() {

    fun getNotesList(): LiveData<DataWrapper<AgentNotesListResModel>> {
        return AgentNotesListRepo()
            .getAgentNotesList()
    }
}
