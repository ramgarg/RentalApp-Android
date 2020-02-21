package com.rental.customer.utils

import com.google.gson.JsonElement
import com.rental.customer.utils.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIServices {

    @POST(Constant.LOGIN)
    fun login():Call<JsonElement>

}