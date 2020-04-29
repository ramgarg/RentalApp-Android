package com.eazyrento.agent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.agent.model.modelclass.AgentAddNoteReqModel
import com.eazyrento.agent.model.modelclass.AgentDashboardResModel
import com.eazyrento.agent.model.modelclass.AgentNotesListResModel
import com.eazyrento.agent.model.repositry.AgentAddNotesRepo
import com.eazyrento.agent.model.repositry.AgentNotesListRepo
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.ProductCategoriesResModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModel
import com.eazyrento.customer.dashboard.model.repositry.CustomerCreateBookingRepo
import com.eazyrento.customer.dashboard.model.repositry.CustomerOrderBookingOrderListRepo
import com.eazyrento.merchant.model.repository.MerchantProductCategoriesRepo
import com.google.gson.JsonElement

/*
class AgentDashboardViewModel : ViewModel() {

    fun getAgentDashboard(): LiveData<DataWrapper<AgentDashboardResModel>> {
        return AgentDashboardRepo().getDeshboardData()
    }
}*/
/*
class MerchantProductCategoriesViewModel:ViewModel(){
    fun getProductCateg(): LiveData<DataWrapper<ProductCategoriesResModel>> {
        return MerchantProductCategoriesRepo()
            .getProductCateg()
    }
}*/

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
