package com.eazyrento.customer.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.customer.dashboard.model.modelclass.HomeResponse
import com.eazyrento.customer.dashboard.model.repositry.OrderRepository


class OrderViewModel : ViewModel() {
    var orderRepository: OrderRepository
    var orderResponseLiveData: LiveData<HomeResponse>

    init {
        orderRepository =
            OrderRepository()
        orderResponseLiveData = orderRepository.getOrderList()
    }



    fun getOrderResponse():LiveData<HomeResponse>{
        return orderResponseLiveData
    }
}