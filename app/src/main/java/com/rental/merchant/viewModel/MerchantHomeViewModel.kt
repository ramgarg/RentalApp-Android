package com.rental.common.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.common.model.modelclass.OrderListing
import com.rental.common.model.repositry.OrderListingRepository

class MerchantHomeViewModel : ViewModel() {
    var orderListingRepository : OrderListingRepository
    var merchantHomeResponseLiveData: LiveData<OrderListing>

    init {
        orderListingRepository = OrderListingRepository()
        merchantHomeResponseLiveData = orderListingRepository.getOrdrListing()
    }


    fun getmerchantHomeOrderList():LiveData<OrderListing>{
        return merchantHomeResponseLiveData
    }
}