package com.rental.customer.payment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.customer.dashboard.model.modelclass.HomeResponse
import com.rental.customer.payment.model.repository.PaymentRepository

class PaymentHistoryViewModel : ViewModel() {

    var paymentRepository: PaymentRepository
    var paymentHistoryResponseLiveData: LiveData<HomeResponse>

    init {
        paymentRepository = PaymentRepository()
        paymentHistoryResponseLiveData = paymentRepository.getPaymentHistoryList()
    }

    fun getPaymentHistoryResponse(): LiveData<HomeResponse> {
        return paymentHistoryResponseLiveData
    }
}