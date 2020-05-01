package com.eazyrento.agent.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.agent.view.activity.AgentOrderRepository
import com.eazyrento.customer.dashboard.model.modelclass.HomeResponse

/*
class AgentOrderViewModel :ViewModel() {

    var agentOrderRepository: AgentOrderRepository
    var agentorderResponseLiveData: LiveData<HomeResponse>

    init {
        agentOrderRepository =
            AgentOrderRepository()
        agentorderResponseLiveData = agentOrderRepository.getOrderList()
    }



    fun getOrderResponse(): LiveData<HomeResponse> {
        return agentorderResponseLiveData
    }
}*/
