package com.rental.customer.myaddress.model.repostory.api

import com.rental.customer.myaddress.model.modelclass.AddressListResModel
import com.rental.webservice.PathURL
import retrofit2.Call
import retrofit2.http.GET

interface AddressApi {

    @GET(PathURL.ADDRESS_LIST)
    fun getAddressList(): Call<AddressListResModel>
}