package com.rental.agent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rental.agent.model.modelclass.AgentDashboardResModel
import com.rental.agent.model.repositry.AgentDashboardRepo
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.common.model.modelclass.ProductCategoriesResModel
import com.rental.merchant.model.modelclass.MerchantDashboardResModel
import com.rental.merchant.model.repository.MerchantDeshboardRepo
import com.rental.merchant.model.repository.MerchantProductCategoriesRepo


class AgentDashboardViewModel : ViewModel() {

    fun getAgentDashboard(): LiveData<DataWrapper<AgentDashboardResModel>> {
        return AgentDashboardRepo().getDeshboardData()
    }
}
class MerchantProductCategoriesViewModel:ViewModel(){
    fun getProductCateg(): LiveData<DataWrapper<ProductCategoriesResModel>> {
        return MerchantProductCategoriesRepo().getProductCateg()
    }
}