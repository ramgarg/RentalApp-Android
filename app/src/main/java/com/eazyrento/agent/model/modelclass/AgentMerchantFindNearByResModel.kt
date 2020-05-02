package com.eazyrento.agent.model.modelclass

data class AgentMerchantFindNearByResModel(
    val merchants_list: List<Merchants>,
    val status: Int
)