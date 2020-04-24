package com.eazyrento.customer.payment.model.repository.api

import com.eazyrento.customer.myaddress.model.modelclass.AddressListResModel
import com.eazyrento.customer.payment.model.modelclass.PaymentListResModel
import com.eazyrento.webservice.PathURL
import retrofit2.Call
import retrofit2.http.GET

interface PaymentApi {

    @GET(PathURL.PaymentList)
    fun getPaymentList(): Call<PaymentListResModel>
}