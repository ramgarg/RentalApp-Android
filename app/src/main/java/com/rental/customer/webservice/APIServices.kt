package com.rental.customer.webservice

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.POST

interface APIServices {

    @POST(Constant.LOGIN)
    fun login():Call<JsonElement>

}