package com.eazyrento.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.common.model.modelclass.OrderListing
import com.eazyrento.common.model.repositry.OrderListingRepo

class OrderListingVM :ViewModel() {

    val orderListingLiveData: LiveData<OrderListing> = OrderListingRepo()
        .getOrdrListing()

}