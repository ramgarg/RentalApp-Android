package com.eazyrento.agent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.agent.model.modelclass.*
import com.eazyrento.agent.model.repositry.AgentAddNotesRepo
import com.eazyrento.agent.model.repositry.AgentMerchantNearByRepo
import com.eazyrento.agent.model.repositry.AgentMerchantsAssignRepo
import com.eazyrento.agent.model.repositry.AgentNotesListRepo
import com.eazyrento.appbiz.retrofitapi.DataWrapper

import com.google.gson.JsonElement


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

    fun createNote(agentAddNoteReqModel: AgentAddNoteReqModel): LiveData<DataWrapper<JsonElement>> {
        return AgentAddNotesRepo()
            .agentNotesAdd(agentAddNoteReqModel)
    }
}

class AgentNotesListViewModel : ViewModel() {

    fun getNotesList(): LiveData<DataWrapper<AgentNotesListResModel>> {
        return AgentNotesListRepo()
            .getAgentNotesList()
    }
}
