package com.eazyrento.customer.payment.model.repository

import androidx.lifecycle.LiveData
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.common.model.modelclass.ProductDetailsResModel
import com.eazyrento.common.model.repositry.api.MasterAPI
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModel
import com.eazyrento.customer.myaddress.model.repostory.api.AddressApi
import com.eazyrento.customer.payment.model.PaymentResponseModel
import com.eazyrento.customer.payment.model.modelclass.PaymentListResModel
import com.eazyrento.customer.payment.model.repository.api.PaymentApi
import com.eazyrento.webservice.ServiceGenrator

class PaymentListRepo : GenericRequestHandler<PaymentListResModel>(){

    fun getPaymentList(): LiveData<DataWrapper<PaymentListResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            PaymentApi::class.java).getPaymentList()
        return doRequest(call)
    }
}