package com.rental.customer.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonElement
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.customer.dashboard.model.modelclass.CustomerCreateBookingReqModel
import com.rental.customer.dashboard.model.modelclass.CustomerOrderDetailsResModel
import com.rental.customer.dashboard.model.modelclass.CustomerOrderListResModel
import com.rental.customer.dashboard.model.modelclass.CustomerOrderListResModelItem
import com.rental.customer.dashboard.model.repositry.CustomerCreateBookingRepo
import com.rental.customer.dashboard.model.repositry.CustomerOrderBookingOrderListRepo
import com.rental.customer.dashboard.model.repositry.CustomerOrderDetailsRepo

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