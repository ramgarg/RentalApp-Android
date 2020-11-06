package com.eazyrento.common.model.repositry.api

import com.eazyrento.agent.model.modelclass.AgentAddNoteReqModelItem
import com.eazyrento.common.model.modelclass.DriverList
import com.eazyrento.common.model.modelclass.MerchantAssignDriver
import com.eazyrento.webservice.PathURL
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

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


    // Merchant Assign drivers
    @POST(PathURL.MERCHANTS_ASSIGN_DRIVER)
    fun merchantAssignDrivers(@Body merchantAssignDriver: MerchantAssignDriver): Call<JsonElement>
}