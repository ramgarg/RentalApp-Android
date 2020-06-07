package com.eazyrento.customer.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonElement
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.ProductID
import com.eazyrento.customer.dashboard.model.modelclass.*
import com.eazyrento.customer.dashboard.model.repositry.*

class CustomerCreateBookingViewModel : ViewModel() {

    fun createBooking(customerCreateBookingReqModel: CustomerCreateBookingReqModel): LiveData<DataWrapper<JsonElement>> {
        return CustomerCreateBookingRepo()
            .createBooking(customerCreateBookingReqModel)
    }
}

class CustomerOrderListViewModel : ViewModel() {

    fun getOrderList(value: Int): LiveData<DataWrapper<CustomerOrderListResModel>> {
        return CustomerOrderBookingOrderListRepo()
            .getBookingOrdersList(value)
    }
}

class CustomerOrderDetailsViewModel : ViewModel() {

    fun getOrderDetails(id: Int): LiveData<DataWrapper<OrderDetailsResModel>> {
        return CustomerOrderDetailsRepo()
            .getCustomerOrderDetail(id)
    }
}

class CustomerWishListViewModel : ViewModel() {

    fun getWishList(): LiveData<DataWrapper<CustomerWishListResModel>> {
        return CustomerWishListRepo()
            .getCustomerWishList()
    }
}

class CustomerWishAddViewModel : ViewModel() {

    fun wishAdd(productID: ProductID): LiveData<DataWrapper<JsonElement>> {
        return CustomerAddWishRepo()
            .customerWishAdd(productID)
    }
}

class CustomerWishDeleteViewModel : ViewModel() {

    fun wishDelete(id: Int): LiveData<DataWrapper<JsonElement>> {
        return CustomerWishDeleteRepo()
            .customerWishDelete(id)
    }
}

class CustomerFeedbackViewModel:ViewModel(){
    fun customerFeedback(customerFeedbackRequestModel: CustomerFeedbackRequestModel): LiveData<DataWrapper<JsonElement>> {
        return CustomerFeedbackRepo()
            .customerFeedback(customerFeedbackRequestModel)
    }

}