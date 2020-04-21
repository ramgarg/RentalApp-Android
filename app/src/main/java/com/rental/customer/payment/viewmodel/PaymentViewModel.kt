package com.rental.customer.payment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.customer.payment.model.modelclass.PaymentListResModel
import com.rental.customer.payment.model.repository.PaymentListRepo

class PaymentListViewModel : ViewModel() {

    fun getAddressList(): LiveData<DataWrapper<PaymentListResModel>> {
        return PaymentListRepo().getPaymentList()
    }
}