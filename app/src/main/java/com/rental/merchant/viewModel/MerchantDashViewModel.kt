package com.rental.merchant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.common.model.modelclass.OrderListing
import com.rental.common.model.repositry.OrderListingRepo

class MerchantDashViewModel : ViewModel() {
    var orderListingRepo : OrderListingRepo
    var merchantHomeResponseLiveData: LiveData<OrderListing>

    init {
        orderListingRepo = OrderListingRepo()
        merchantHomeResponseLiveData = orderListingRepo.getOrdrListing()
    }


    fun getmerchantHomeOrderList():LiveData<OrderListing>{
        return merchantHomeResponseLiveData
    }

}