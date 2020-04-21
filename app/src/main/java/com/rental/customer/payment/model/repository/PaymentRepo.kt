package com.rental.customer.payment.model.repository

import androidx.lifecycle.LiveData
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.appbiz.retrofitapi.GenericRequestHandler
import com.rental.common.model.modelclass.ProductDetailsResModel
import com.rental.common.model.repositry.api.MasterAPI
import com.rental.customer.myaddress.model.modelclass.AddressListResModel
import com.rental.customer.myaddress.model.repostory.api.AddressApi
import com.rental.customer.payment.model.PaymentResponseModel
import com.rental.customer.payment.model.modelclass.PaymentListResModel
import com.rental.customer.payment.model.repository.api.PaymentApi
import com.rental.webservice.ServiceGenrator

class PaymentListRepo : GenericRequestHandler<PaymentListResModel>(){

    fun getPaymentList(): LiveData<DataWrapper<PaymentListResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(PaymentApi::class.java).getPaymentList()
        return doRequest(call)
    }
}