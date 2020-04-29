package com.eazyrento.agent.model.repositry

import androidx.lifecycle.LiveData
import com.eazyrento.agent.model.modelclass.AgentAddNoteReqModel
import com.eazyrento.agent.model.modelclass.AgentNotesListResModel
import com.eazyrento.agent.model.repositry.api.AgentAPI

import com.eazyrento.appbiz.retrofitapi.DataWrapper
import com.eazyrento.appbiz.retrofitapi.GenericRequestHandler
import com.eazyrento.common.model.modelclass.ProductCategoriesResModel
import com.eazyrento.common.model.modelclass.ProductID
import com.eazyrento.customer.dashboard.model.modelclass.CustomerWishListResModel
import com.eazyrento.customer.dashboard.model.repositry.api.CustomerAPI
import com.eazyrento.merchant.model.repository.api.MerchantAPI
import com.eazyrento.webservice.ServiceGenrator
import com.google.gson.JsonElement

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
class AgentNotesListRepo :
    GenericRequestHandler<AgentNotesListResModel>(){

    fun getAgentNotesList(): LiveData<DataWrapper<AgentNotesListResModel>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            AgentAPI::class.java).getAgentNotes()
        return doRequest(call)
    }

}

class AgentAddNotesRepo :
    GenericRequestHandler<JsonElement>(){

    fun agentNotesAdd(agentAddNoteReqModel: AgentAddNoteReqModel): LiveData<DataWrapper<JsonElement>> {
//        AppBizLogger.log(AppBizLogger.LoggingType.DEBUG, Gson().toJson(loginUserReqModel))
        val call = ServiceGenrator.client.create(
            AgentAPI::class.java).createAgentNote(agentAddNoteReqModel)
        return doRequest(call)
    }

}



