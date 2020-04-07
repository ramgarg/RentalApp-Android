package com.rental.customer.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.customer.dashboard.model.modelclass.OrderSummaryResponseModel
import com.rental.customer.dashboard.model.repositry.OrderSummaryRepository


class OrderSummaryViewModel : ViewModel() {
    var orderSummaryRepository: OrderSummaryRepository
    var orderSummaryResponseLiveData: LiveData<OrderSummaryResponseModel>

    init {
        orderSummaryRepository = OrderSummaryRepository()
        orderSummaryResponseLiveData = orderSummaryRepository.getOrderSummary()
    }



    fun getOrderSummaryResponse():LiveData<OrderSummaryResponseModel>{
        return orderSummaryResponseLiveData
    }
}