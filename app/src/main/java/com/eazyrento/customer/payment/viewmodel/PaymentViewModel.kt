package com.eazyrento.customer.payment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.customer.payment.model.modelclass.PaymentListResModel
import com.eazyrento.customer.payment.model.repository.PaymentListRepo

class PaymentListViewModel : ViewModel() {

    fun getAddressList(): LiveData<DataWrapper<PaymentListResModel>> {
        return PaymentListRepo().getPaymentList()
    }
}