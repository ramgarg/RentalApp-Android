package com.rental.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.common.model.modelclass.OrderListing
import com.rental.common.model.repositry.OrderListingRepo

class OrderListingVM :ViewModel() {

    val orderListingLiveData: LiveData<OrderListing> = OrderListingRepo().getOrdrListing()

}