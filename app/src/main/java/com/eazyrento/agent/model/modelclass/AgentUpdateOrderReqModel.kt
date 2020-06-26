package com.eazyrento.agent.model.modelclass

data class AgentUpdateOrderReqModel(
    val start_date: String,
    val start_time: String,
    val end_date: String,
    val end_time: String
)