package com.rental.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.common.model.modelclass.OrderListing
import com.rental.common.model.repositry.OrderListingRepository

class AgentHomeViewModel :ViewModel() {
   var orderListingRepository:OrderListingRepository

    var agentHomeResponseLiveData: LiveData<OrderListing>

    init {
        orderListingRepository = OrderListingRepository()
        agentHomeResponseLiveData = orderListingRepository.getOrdrListing()
    }



    fun getAgentHomeOrderList():LiveData<OrderListing>{
        return agentHomeResponseLiveData
    }
}