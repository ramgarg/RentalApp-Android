package com.eazyrento.customer.payment.model.repository

import androidx.lifecycle.LiveData
import com.eazyrento.EazyRantoApplication
import com.eazyrento.Session
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.common.model.modelclass.ProductDetailsResModel
import com.eazyrento.common.model.repositry.api.MasterAPI
import com.eazyrento.common.view.UserInfoAPP
import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModel
import com.eazyrento.customer.myaddress.model.repostory.api.AddressApi
import com.eazyrento.customer.payment.model.PaymentResponseModel
import com.eazyrento.customer.payment.model.modelclass.PaymentListResModel
import com.eazyrento.customer.payment.model.repository.api.PaymentApi
import com.eazyrento.webservice.ServiceGenrator
import retrofit2.Call

class PaymentListRepo : GenericRequestHandler<PaymentListResModel>(){

    fun getPaymentList(orderID:String?): LiveData<DataWrapper<PaymentListResModel>> {

        var call:Call<PaymentListResModel>

        if (Session.getInstance(EazyRantoApplication.context)?.getUserRole()==UserInfoAPP.CUSTOMER) {
            if (orderID ==null) {
                call = ServiceGenrator.client.create(
                    PaymentApi::class.java
                ).getPaymentList()
            }else{
                call = ServiceGenrator.client.create(
                    PaymentApi::class.java
                ).getPaymentListByID(orderID)
            }


        }else{
             call = ServiceGenrator.client.create(
                PaymentApi::class.java
            ).getAgentPaymentListByOrderID(orderID!!)
        }

        return doRequest(call)
    }
}