package com.rental.agent.model.repositry.api

import com.rental.agent.model.modelclass.AgentDashboardResModel
import com.rental.merchant.model.modelclass.MerchantDashboardResModel
import com.rental.webservice.PathURL
import retrofit2.Call
import retrofit2.http.GET

interface AgentAPI {

    @GET(PathURL.AgentDashboard)
    fun getAgentDashboardData(): Call<AgentDashboardResModel>
}