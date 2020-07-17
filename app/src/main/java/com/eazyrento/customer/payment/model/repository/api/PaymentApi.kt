package com.eazyrento.customer.payment.model.repository.api

import com.eazyrento.customer.payment.model.modelclass.*
import com.eazyrento.webservice.PathURL
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PaymentApi {

    // customer
    @GET(PathURL.PaymentList)
    fun getPaymentList(): Call<PaymentListResModel>

    @GET(PathURL.PaymentListByID)
    fun getPaymentListByID(@Path("id") id: String): Call<PaymentListResModel>

    @POST(PathURL.MakePayment)
    fun makePayment(@Path("id") id: Int,@Body customerMakePaymentReqModel: CustomerMakePaymentReqModel): Call<JsonElement>

    @POST(PathURL.PAYMENT_GETWAY_CHECKOUT_ID)
    fun paymentGetwayCheckoutID(@Path("order_id") order_id: Int,@Body req: PaymentGetwayCheckoutIDReqModel): Call<PaymentGetwayCheckoutIDResModel>

    //agent
    @GET(PathURL.AgentPayments)
    fun getAgentPaymentListByOrderID(@Path("id") id: String): Call<PaymentListResModel>

    @POST(PathURL.AgentRequestPayment)
    fun requestPayment(@Body requestModel: AgentMakePaymentReqModel): Call<JsonElement>

}