package com.eazyrento.common.model.repositry.api

import com.eazyrento.common.model.modelclass.DriverList
import com.eazyrento.webservice.PathURL
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DriverApis {

    // Customer near by drivers
    @GET(PathURL.CUSTOMER_SHOW_NEAR_BY_DRIVERS)
    fun getNearByDriversToCustomer(@Query ("latitude") lati:Double, @Query ("longitude") longi:Double): Call<DriverList>

    // Customer near by drivers
    @GET(PathURL.AGENT_SHOW_NEAR_BY_DRIVERS)
    fun getNearByDriversToAgent(@Path ("booking_id") bookingID:Int): Call<DriverList>


    // Customer near by drivers
    @GET(PathURL.MERCHANTS_DRIVER)
    fun getMerchantDrivers(@Query ("latitude") lati:Double, @Query ("longitude") longi:Double): Call<DriverList>
}