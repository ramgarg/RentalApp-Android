package com.eazyrento.merchant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.customer.dashboard.model.modelclass.HomeResponse
import com.eazyrento.merchant.model.repository.MerchantOrderRepository

class MerchantOrderViewModel : ViewModel() {
    var merchantOrderRepository: MerchantOrderRepository
    var merchantorderResponseLiveData: LiveData<HomeResponse>

    init {
        merchantOrderRepository =
            MerchantOrderRepository()
        merchantorderResponseLiveData = merchantOrderRepository.getOrderList()
    }



    fun getOrderResponse(): LiveData<HomeResponse> {
        return merchantorderResponseLiveData
    }
}