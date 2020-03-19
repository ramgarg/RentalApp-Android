package com.rental.merchant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import com.rental.customer.dashboard.model.repositry.OrderRepository
import com.rental.merchant.model.repository.MerchantOrderRepository

class MerchantOrderViewModel : ViewModel() {
    var merchantOrderRepository: MerchantOrderRepository
    var merchantorderResponseLiveData: LiveData<HomeResponse>

    init {
        merchantOrderRepository = MerchantOrderRepository()
        merchantorderResponseLiveData = merchantOrderRepository.getOrderList()
    }



    fun getOrderResponse(): LiveData<HomeResponse> {
        return merchantorderResponseLiveData
    }
}