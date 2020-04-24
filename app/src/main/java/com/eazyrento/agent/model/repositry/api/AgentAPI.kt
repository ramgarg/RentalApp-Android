package com.eazyrento.agent.model.repositry.api

import com.eazyrento.agent.model.modelclass.AgentDashboardResModel
import com.eazyrento.common.model.modelclass.BookingDashboardResModel
import com.eazyrento.common.model.modelclass.BookingListResModel
import com.eazyrento.webservice.PathURL
import retrofit2.Call
import retrofit2.http.GET

interface AgentAPI {

    @GET(PathURL.AgentDashboard)
    fun getAgentDashboardData(): Call<BookingDashboardResModel>

    //Booking list

    @GET(PathURL.AgentMyBookings)
    fun getCustomerBookingList(): Call<BookingListResModel>
}