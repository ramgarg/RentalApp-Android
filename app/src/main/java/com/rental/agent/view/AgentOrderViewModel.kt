package com.rental.agent.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.agent.view.activity.AgentOrderRepository
import com.rental.customer.dashboard.model.modelclass.HomeResponse

class AgentOrderViewModel :ViewModel() {

    var agentOrderRepository: AgentOrderRepository
    var agentorderResponseLiveData: LiveData<HomeResponse>

    init {
        agentOrderRepository = AgentOrderRepository()
        agentorderResponseLiveData = agentOrderRepository.getOrderList()
    }



    fun getOrderResponse(): LiveData<HomeResponse> {
        return agentorderResponseLiveData
    }
}