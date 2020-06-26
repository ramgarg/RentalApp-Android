package com.eazyrento.agent.model.repositry.api

import com.eazyrento.agent.model.modelclass.*
import com.eazyrento.common.model.modelclass.AcceptanceDeclineReqModel
import com.eazyrento.common.model.modelclass.BookingDashboardResModel
import com.eazyrento.common.model.modelclass.BookingListResModel
import com.eazyrento.customer.dashboard.model.modelclass.OrderDetailsResModel
import com.eazyrento.customer.dashboard.model.modelclass.CustomerOrderListResModel
import com.eazyrento.customer.dashboard.model.modelclass.SubOrderReqResModel
import com.eazyrento.customer.dashboard.model.modelclass.SubOrderUpdateReqModel
import com.eazyrento.webservice.PathURL
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface AgentAPI {

    @GET(PathURL.AgentDashboard)
    fun getAgentDashboardData(): Call<BookingDashboardResModel>

    @POST(PathURL.AGENT_ACCEPTANCE_DECLINE)
    fun acceptanceDelete(@Body acceptanceDeclineReqModel: AcceptanceDeclineReqModel): Call<JsonElement>

    //Booking list

    @GET(PathURL.AgentMyBookings)
    fun getCustomerBookingList(): Call<BookingListResModel>

    //Add Note

    @POST(PathURL.UserAddNotes)
    fun createAgentNote(@Body agentAddNoteReqModel: AgentAddNoteReqModelItem): Call<AgentNotesListResModelItem>

    @GET(PathURL.UserNotesList)
    fun getAgentNotes(): Call<AgentNotesListResModel>

    @DELETE(PathURL.UserDeleteNote)
    fun deleteNote(@Path("id") id: Int): Call<JsonElement>

    @PUT(PathURL.UserUpdateNote)
    fun updateNote(@Path("id") id: Int,@Body agentAddNoteReqModelItem: AgentAddNoteReqModelItem): Call<AgentNotesListResModelItem>


    @GET(PathURL.AgentMerchants)
    fun getAgentMerchantsNearBy(@Path("id") id:Int) : Call<AgentMerchantFindNearByResModel>

    @POST(PathURL.AssignMerchants)
    fun assignMerchants(@Body assignMerchantsReqModel: AssignMerchantsReqModel) : Call<JsonElement>

    @GET(PathURL.AgentOrders)
    fun getCustomerOrdersList(@Path ("value") value:Int): Call<CustomerOrderListResModel>

    /*
     *agent orders details
     * */
    @GET(PathURL.AgentOrderDetail)
    fun getCustomerOrderDetail(@Path("id") id: Int): Call<OrderDetailsResModel>

    //update agent order
    @PUT(PathURL.AgentOrderDetail)
    fun updateCustomerOrderDetail(@Path("id") id: Int,@Body req:AgentUpdateOrderReqModel): Call<JsonElement>

    // get sub order details
    @GET(PathURL.AgentSubOrderDetail)
    fun getSubOrderDetail(@Path("sub_order_id") sub_order_id: Int): Call<SubOrderReqResModel>
    // update sub order details
    @PUT(PathURL.AgentSubOrderDetail)
    fun updateSubOrderDetail(@Path("sub_order_id") sub_order_id: Int,@Body req: SubOrderUpdateReqModel): Call<JsonElement>

    @GET(PathURL.AgentBookingDetail)
    fun getAgentBookingDetail(@Path("id") id: Int): Call<OrderDetailsResModel>

    @POST(PathURL.AgentFeedback)
    fun agentFeedback(@Body agentFeedbackReqModel: AgentFeedbackReqModel): Call<JsonElement>

    // payment recived or not
    @POST(PathURL.AgentCollectCashPayment)
    fun paymentRecivedOrNot(@Body paymentRecivedOrNotReqModel: PaymentRecivedOrNotReqModel): Call<JsonElement>
}