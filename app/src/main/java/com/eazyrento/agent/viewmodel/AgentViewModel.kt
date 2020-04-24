package com.eazyrento.agent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eazyrento.agent.model.modelclass.AgentDashboardResModel
import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.common.model.modelclass.ProductCategoriesResModel
import com.eazyrento.merchant.model.repository.MerchantProductCategoriesRepo

/*
class AgentDashboardViewModel : ViewModel() {

    fun getAgentDashboard(): LiveData<DataWrapper<AgentDashboardResModel>> {
        return AgentDashboardRepo().getDeshboardData()
    }
}*/
class MerchantProductCategoriesViewModel:ViewModel(){
    fun getProductCateg(): LiveData<DataWrapper<ProductCategoriesResModel>> {
        return MerchantProductCategoriesRepo()
            .getProductCateg()
    }
}