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
import com.eazyrento.customer.payment.model.modelclass.AgentMakePaymentReqModel
import com.eazyrento.customer.payment.model.modelclass.BaseMakePaymentModel
import com.eazyrento.customer.payment.model.modelclass.CustomerMakePaymentReqModel
import com.eazyrento.customer.payment.model.modelclass.PaymentListResModel
import com.eazyrento.customer.payment.model.repository.api.PaymentApi
import com.eazyrento.webservice.ServiceGenrator
import com.google.gson.JsonElement
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

class MakePaymentRepo : GenericRequestHandler<JsonElement>() {

    fun makePayment(bookingID: Int,req: BaseMakePaymentModel): LiveData<DataWrapper<JsonElement>> {

        var call:Call<JsonElement>

        if (req is CustomerMakePaymentReqModel) {
            call = ServiceGenrator.client.create(
                PaymentApi::class.java
            ).makePayment(bookingID, req)
        }else {
            val obj = req as AgentMakePaymentReqModel
            call = ServiceGenrator.client.create(
                PaymentApi::class.java
            ).requestPayment(bookingID, obj)

        }
        return doRequest(call)
    }
}