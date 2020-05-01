package com.eazyrento.agent.model.repositry.api

import com.eazyrento.agent.model.modelclass.AgentAddNoteReqModel
import com.eazyrento.agent.model.modelclass.AgentDashboardResModel
import com.eazyrento.agent.model.modelclass.AgentNotesListResModel
import com.eazyrento.common.model.modelclass.AcceptanceDeclineReqModel
import com.eazyrento.common.model.modelclass.BookingDashboardResModel
import com.eazyrento.common.model.modelclass.BookingListResModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerCreateBookingReqModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModel
import com.eazyrento.webservice.PathURL
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AgentAPI {

    @GET(PathURL.AgentDashboard)
    fun getAgentDashboardData(): Call<BookingDashboardResModel>

    @POST(PathURL.AGENT_ACCEPTANCE_DECLINE)
    fun acceptanceDelete(@Body acceptanceDeclineReqModel: AcceptanceDeclineReqModel): Call<JsonElement>

    //Booking list

    @GET(PathURL.AgentMyBookings)
    fun getCustomerBookingList(): Call<BookingListResModel>

    //Add Note

    @POST(PathURL.AgentAddNotes)
    fun createAgentNote(@Body agentAddNoteReqModel: AgentAddNoteReqModel): Call<JsonElement>

    @GET(PathURL.AgentNotesList)
    fun getAgentNotes(): Call<AgentNotesListResModel>
}