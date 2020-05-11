package com.eazyrento.customer.payment.model.repository.api

import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModel
import com.eazyrento.customer.payment.model.modelclass.PaymentListResModel
import com.eazyrento.webservice.PathURL
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PaymentApi {

    // customer
    @GET(PathURL.PaymentList)
    fun getPaymentList(): Call<PaymentListResModel>

    @GET(PathURL.PaymentListByID)
    fun getPaymentListByID(@Path("id") id: String): Call<PaymentListResModel>


    //agent
    @GET(PathURL.AgentPayments)
    fun getAgentPaymentListByOrderID(@Path("id") id: String): Call<PaymentListResModel>
}