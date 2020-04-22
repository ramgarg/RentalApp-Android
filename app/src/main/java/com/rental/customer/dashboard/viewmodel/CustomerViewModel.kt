package com.rental.customer.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonElement
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.customer.dashboard.model.modelclass.*
import com.rental.customer.dashboard.model.repositry.CustomerCreateBookingRepo
import com.rental.customer.dashboard.model.repositry.CustomerOrderBookingOrderListRepo
import com.rental.customer.dashboard.model.repositry.CustomerOrderDetailsRepo
import com.rental.customer.dashboard.model.repositry.CustomerWishListRepo

class CustomerCreateBookingViewModel : ViewModel() {

    fun createBooking(customerCreateBookingReqModel: CustomerCreateBookingReqModel): LiveData<DataWrapper<JsonElement>> {
        return CustomerCreateBookingRepo().createBooking(customerCreateBookingReqModel)
    }
}

class CustomerOrderListViewModel : ViewModel() {

    fun getOrderList(value: Int): LiveData<DataWrapper<CustomerOrderListResModel>> {
        return CustomerOrderBookingOrderListRepo().getBookingOrdersList(value)
    }
}

class CustomerOrderDetailsViewModel : ViewModel() {

    fun getOrderDetails(id: Int): LiveData<DataWrapper<CustomerOrderDetailsResModel>> {
        return CustomerOrderDetailsRepo().getCustomerOrderDetail(id)
    }
}

class CustomerWishListViewModel : ViewModel() {

    fun getWishList(): LiveData<DataWrapper<CustomerWishListResModel>> {
        return CustomerWishListRepo().getCustomerWishList()
    }
}