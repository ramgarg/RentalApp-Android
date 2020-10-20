package com.eazyrento.tracking.data.api

import com.eazyrento.tracking.data.model.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("maps/api/directions/json")
    fun getDirections(
        @Query("mode") mode: String?,
        @Query("transit_routing_preference") routingPreference: String?,
        @Query("origin") origin: String?,
        @Query("destination") destination: String?,
        @Query("key") apiKey: String?
    ): Single<Response?>?
}