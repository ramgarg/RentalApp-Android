package com.eazyrento.agent.model.repositry

import androidx.lifecycle.LiveData

import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.common.model.modelclass.ProductCategoriesResModel
import com.eazyrento.merchant.model.repository.api.MerchantAPI
import com.eazyrento.webservice.ServiceGenrator

/*class AgentDashboardRepo :GenericRequestHandler<AgentDashboardResModel>(){

    fun getDeshboardData(): LiveData<DataWrapper<AgentDashboardResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(AgentAPI::class.java).getAgentDashboardData()
        return doRequest(call)
    }


}*/

/*class MerchantProductCategoriesRepo : GenericRequestHandler<ProductCategoriesResModel>(){

    fun getProductCateg( ): LiveData<DataWrapper<ProductCategoriesResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            MerchantAPI::class.java).getProductCategory()
        return doRequest(call)
    }
}*/

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


