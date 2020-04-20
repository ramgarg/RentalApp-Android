package com.rental.agent.model.repositry

import androidx.lifecycle.LiveData
import com.rental.agent.model.modelclass.AgentDashboardResModel
import com.rental.agent.model.repositry.api.AgentAPI
import com.rental.appbiz.retrofitapi.DataWrapper
import com.rental.appbiz.retrofitapi.GenericRequestHandler
import com.rental.common.model.modelclass.ProductCategoriesResModel
import com.rental.merchant.model.modelclass.MerchantDashboardResModel
import com.rental.merchant.model.repository.api.MerchantAPI
import com.rental.webservice.ServiceGenrator

class AgentDashboardRepo :GenericRequestHandler<AgentDashboardResModel>(){

    fun getDeshboardData(): LiveData<DataWrapper<AgentDashboardResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(AgentAPI::class.java).getAgentDashboardData()
        return doRequest(call)
    }


}

class MerchantProductCategoriesRepo : GenericRequestHandler<ProductCategoriesResModel>(){

    fun getProductCateg( ): LiveData<DataWrapper<ProductCategoriesResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(MerchantAPI::class.java).getProductCategory()
        return doRequest(call)
    }
}

/*class MerchantProductCategories :GenericRequestHandler<ProductCategoriesResModel>(){

    fun getBookingOrdersList(value: Int): LiveData<DataWrapper<ProductCategoriesResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(MerchantAPI::class.java).getProductCategory()
        return doRequest(call)
    }

}*/

/*class CustomerOrderDetailsRepo :GenericRequestHandler<CustomerOrderDetailsResModel>(){

    fun getCustomerOrderDetail(value: Int): LiveData<DataWrapper<CustomerOrderDetailsResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(MerchantAPI::class.java).getCustomerOrderDetail(value)
        return doRequest(call)
    }

}*/


