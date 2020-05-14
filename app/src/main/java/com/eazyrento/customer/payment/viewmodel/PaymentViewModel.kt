package com.eazyrento.customer.payment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.customer.payment.model.modelclass.BaseMakePaymentModel
import com.eazyrento.customer.payment.model.modelclass.CustomerMakePaymentReqModel
import com.eazyrento.customer.payment.model.modelclass.PaymentListResModel
import com.eazyrento.customer.payment.model.repository.MakePaymentRepo
import com.eazyrento.customer.payment.model.repository.PaymentListRepo
import com.google.gson.JsonElement

class PaymentListViewModel : ViewModel() {

    fun getPaymentList(orderID:String?): LiveData<DataWrapper<PaymentListResModel>> {
        return PaymentListRepo().getPaymentList(orderID)
    }
}

class MakePaymentViewModel : ViewModel() {

    fun makePayment(orderID:Int,customerMakePaymentReqModel: BaseMakePaymentModel): LiveData<DataWrapper<JsonElement>> {
        return MakePaymentRepo().makePayment(orderID,customerMakePaymentReqModel)
    }
}