package com.eazyrento.customer.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.customer.dashboard.model.modelclass.OrderSummaryResponseModel
import com.eazyrento.customer.dashboard.model.repositry.OrderSummaryRepository


class OrderSummaryViewModel : ViewModel() {
    var orderSummaryRepository: OrderSummaryRepository
    var orderSummaryResponseLiveData: LiveData<OrderSummaryResponseModel>

    init {
        orderSummaryRepository =
            OrderSummaryRepository()
        orderSummaryResponseLiveData = orderSummaryRepository.getOrderSummary()
    }



    fun getOrderSummaryResponse():LiveData<OrderSummaryResponseModel>{
        return orderSummaryResponseLiveData
    }
}