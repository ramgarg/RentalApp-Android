package com.rental.customer.payment.model.repository.api

import com.rental.customer.myaddress.model.modelclass.AddressListResModel
import com.rental.customer.payment.model.modelclass.PaymentListResModel
import com.rental.webservice.PathURL
import retrofit2.Call
import retrofit2.http.GET

interface PaymentApi {

    @GET(PathURL.PaymentList)
    fun getPaymentList(): Call<PaymentListResModel>
}