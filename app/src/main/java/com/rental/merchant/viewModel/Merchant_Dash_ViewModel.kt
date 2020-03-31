package com.rental.merchant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.common.model.modelclass.OrderListing
import com.rental.common.model.repositry.OrderListingRepository
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import com.rental.merchant.model.repository.MerchantHomeRepository

class Merchant_Dash_ViewModel : ViewModel() {
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